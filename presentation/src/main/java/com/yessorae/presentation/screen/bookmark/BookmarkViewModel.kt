package com.yessorae.presentation.screen.bookmark

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yessorae.domain.usecase.bookmark.GetBookmarkImageUseCase
import com.yessorae.domain.usecase.common.DeleteBookmarkImageUseCase
import com.yessorae.presentation.common.util.createSaveableMutableStateFlow
import com.yessorae.presentation.screen.bookmark.model.BookmarkScreenState
import com.yessorae.presentation.screen.bookmark.model.BookmarkScreenUserAction
import com.yessorae.presentation.screen.bookmark.model.asSelectableUiModel
import com.yessorae.presentation.screen.bookmark.model.asUiModel
import com.yessorae.presentation.screen.saerch.SearchViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getBookmarkImageUseCase: GetBookmarkImageUseCase,
    private val deleteBookmarkImageUseCase: DeleteBookmarkImageUseCase,
    state: SavedStateHandle
) : ViewModel() {
    private val _searchKeyword = state.createSaveableMutableStateFlow("searchKeyword", "")
    val searchKeyword = _searchKeyword.asStateFlow()

    private val _screenState: MutableStateFlow<BookmarkScreenState> =
        MutableStateFlow(BookmarkScreenState.Loading)
    val screenState = _screenState
        .onSubscription {
            subscribeBookmarkImages()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = BookmarkScreenState.Loading
        )

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun subscribeBookmarkImages() {
        viewModelScope.launch {
            searchKeyword
                .debounce(SearchViewModel.DEBOUNCE_TIME_MILLIS)
                .flatMapLatest { keyword ->
                    getBookmarkImageUseCase(keyword = keyword).map { list ->
                        Pair(
                            keyword,
                            list.map { imageSearchResult ->
                                imageSearchResult.asUiModel()
                            }
                        )
                    }
                }
                .catch {
                    handleError()
                }
                .collect { (keyword, bookmarkUiList) ->
                    if (bookmarkUiList.isEmpty()) {
                        _screenState.value = if (keyword.isEmpty()) {
                            BookmarkScreenState.EmptyBookmarkImage
                        } else {
                            BookmarkScreenState.EmptyFilteredBookmarkImage
                        }
                        return@collect
                    }

                    _screenState.value = BookmarkScreenState.Success(
                        bookmarkImages = bookmarkUiList
                    )
                }
        }
    }

    fun handleUserAction(userAction: BookmarkScreenUserAction) {
        val currentState = screenState.value

        when (userAction) {
            is BookmarkScreenUserAction.ChangeSearchKeyword -> {
                _searchKeyword.value = userAction.keyword
            }

            is BookmarkScreenUserAction.ClearSearchKeyword -> {
                _searchKeyword.value = ""
            }

            is BookmarkScreenUserAction.ClickDeleteSingleBookmark -> {
                viewModelScope.launch {
                    try {
                        deleteBookmarkImageUseCase(listOf(userAction.bookmark.imageUrl))
                    } catch (e: Exception) {
                        handleError()
                    }
                }
            }

            is BookmarkScreenUserAction.ClickEditMode -> {
                if (currentState !is BookmarkScreenState.Success) return

                _screenState.value = BookmarkScreenState.Edit(
                    selectableBookmarkImages = currentState.bookmarkImages.map { bookmarkImageUi ->
                        bookmarkImageUi.asSelectableUiModel(selected = false)
                    }
                )
            }

            is BookmarkScreenUserAction.ClickBookmarkImageForMultipleDelete -> {
                if (currentState !is BookmarkScreenState.Edit) return

                val selectableBookmarkImages = currentState.selectableBookmarkImages.toMutableList()
                val clickedBookmarkImage = userAction.selectableBookmarkImageUi.copy(selected = userAction.selectableBookmarkImageUi.selected.not())
                val index = selectableBookmarkImages.indexOf(userAction.selectableBookmarkImageUi)
                runCatching { selectableBookmarkImages[index] = clickedBookmarkImage }

                _screenState.value = BookmarkScreenState.Edit(
                    selectableBookmarkImages = selectableBookmarkImages.toList(),
                    showDeleteButton = selectableBookmarkImages.any { it.selected }
                )
            }

            is BookmarkScreenUserAction.ClickCancelEditMode -> {
                if (currentState !is BookmarkScreenState.Edit) return

                _screenState.value = BookmarkScreenState.Success(
                    bookmarkImages = currentState.selectableBookmarkImages.map { it.bookmarkImageUi }
                )
            }

            is BookmarkScreenUserAction.ClickDeleteMultipleBookmark -> {
                if (currentState !is BookmarkScreenState.Edit) return

                viewModelScope.launch {
                    try {
                        deleteBookmarkImageUseCase(
                            currentState.selectableBookmarkImages
                                .filter { it.selected }
                                .map {
                                    it.bookmarkImageUi.imageUrl
                                }
                        )
                    } catch (e: Exception) {
                        handleError()
                    }
                }
            }
        }
    }

    private fun handleError() {
        _screenState.value = BookmarkScreenState.Error
    }
}
