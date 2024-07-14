package com.yessorae.presentation.screen.saerch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.yessorae.domain.usecase.SearchImageUseCase
import com.yessorae.presentation.screen.saerch.model.ImageUi
import com.yessorae.presentation.screen.saerch.model.SearchScreenUserAction
import com.yessorae.presentation.screen.saerch.model.asImageUi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchImageUseCase: SearchImageUseCase
) : ViewModel() {
    private val _searchKeyword = MutableStateFlow("")
    val searchKeyword = _searchKeyword.asStateFlow()

    val showImageSearchResultUi = _searchKeyword
        .map { it.isNotBlank() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = false
        )

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val pagedImageUiFlow: Flow<PagingData<ImageUi>> = searchKeyword
        .debounce(DEBOUNCE_TIME_MILLIS)
        .filter { it.isNotBlank() }
        .distinctUntilChanged()
        .flatMapLatest { keyword ->
            searchImageUseCase(keyword).map { pagingData ->
                pagingData.map { imageResult ->
                    imageResult.asImageUi()
                }
            }
        }
        .cachedIn(viewModelScope)

    fun handleUserAction(userAction: SearchScreenUserAction) {
        when (userAction) {
            is SearchScreenUserAction.SearchKeywordChange -> {
                _searchKeyword.value = userAction.keyword
            }

            is SearchScreenUserAction.SearchKeywordClear -> {
                _searchKeyword.value = ""
            }
        }
    }

    companion object {
        const val DEBOUNCE_TIME_MILLIS = 1000L
    }
}
