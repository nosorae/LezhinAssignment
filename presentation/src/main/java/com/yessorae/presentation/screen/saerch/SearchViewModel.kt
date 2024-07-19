package com.yessorae.presentation.screen.saerch

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.yessorae.domain.usecase.common.DeleteBookmarkImageUseCase
import com.yessorae.domain.usecase.search.AddBookmarkImageUseCase
import com.yessorae.domain.usecase.search.GetBookmarkUrlSetUseCase
import com.yessorae.domain.usecase.search.SearchImageUseCase
import com.yessorae.presentation.common.util.createSaveableMutableStateFlow
import com.yessorae.presentation.screen.saerch.model.ImageUi
import com.yessorae.presentation.screen.saerch.model.SearchScreenUserAction
import com.yessorae.presentation.screen.saerch.model.asDomainModel
import com.yessorae.presentation.screen.saerch.model.asUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchImageUseCase: SearchImageUseCase,
    private val addBookmarkImageUseCase: AddBookmarkImageUseCase,
    private val deleteBookmarkImageUseCase: DeleteBookmarkImageUseCase,
    private val getBookmarkUrlSetUseCase: GetBookmarkUrlSetUseCase,
    state: SavedStateHandle
) : ViewModel() {
    private val _searchKeyword = state.createSaveableMutableStateFlow("searchKeyword", "")
    val searchKeyword = _searchKeyword.asStateFlow()

    val showImageSearchResultUi = searchKeyword
        .map { keyword ->
            val isNotEmpty = keyword.isNotEmpty()
            if (isNotEmpty) delay(DEBOUNCE_TIME_MILLIS)
            isNotEmpty
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = false
        )

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val pagedImageUiFlow: Flow<PagingData<ImageUi>> =
        combine(
            searchKeyword
                .debounce(DEBOUNCE_TIME_MILLIS)
                .filter { it.isNotBlank() }
                .flatMapLatest { keyword ->
                    searchImageUseCase(keyword)
                }
                .cachedIn(viewModelScope),
            getBookmarkUrlSetUseCase()
        ) { pagingData, bookmarkImageUrlSet ->
            pagingData.map { imageSearchResult ->
                imageSearchResult.asUiModel(
                    isBookmark = bookmarkImageUrlSet.contains(imageSearchResult.imageUrl)
                )
            }
        }

    fun handleUserAction(userAction: SearchScreenUserAction) =
        viewModelScope.launch {
            when (userAction) {
                is SearchScreenUserAction.ChangeSearchKeyword -> {
                    _searchKeyword.value = userAction.keyword
                }

                is SearchScreenUserAction.ClearSearchKeyword -> {
                    _searchKeyword.value = ""
                }

                is SearchScreenUserAction.ClickBookmark -> {
                    val imageUi = userAction.imageUi
                    if (imageUi.isBookmark) {
                        deleteBookmarkImageUseCase(listOf(imageUi.imageUrl))
                    } else {
                        addBookmarkImageUseCase(imageUi.asDomainModel())
                    }
                }
            }
        }

    companion object {
        const val DEBOUNCE_TIME_MILLIS = 1000L
    }
}
