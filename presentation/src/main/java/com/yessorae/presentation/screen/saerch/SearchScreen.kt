package com.yessorae.presentation.screen.saerch

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.yessorae.presentation.R
import com.yessorae.presentation.common.component.LezhinAssignMainTopAppBar
import com.yessorae.presentation.common.component.LezhinAssignmentCircularProgressIndicator
import com.yessorae.presentation.common.component.LezhinAssignmentErrorGuide
import com.yessorae.presentation.common.component.LezhinAssignmentNormalTextGuide
import com.yessorae.presentation.common.component.LezhinAssignmentTextField
import com.yessorae.presentation.common.component.LezhinLazyVerticalStaggeredGrid
import com.yessorae.presentation.common.util.DevicePreviews
import com.yessorae.presentation.common.util.ThemePreviews
import com.yessorae.presentation.screen.saerch.component.ImageSearchResultListItem
import com.yessorae.presentation.screen.saerch.model.ImageUi
import com.yessorae.presentation.screen.saerch.model.SearchScreenUserAction
import com.yessorae.presentation.theme.Dimen
import com.yessorae.presentation.theme.LezhinAssignmentTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun SearchRoute(viewModel: SearchViewModel = hiltViewModel()) {
    val pagedImageSearchResult = viewModel.pagedImageUiFlow.collectAsLazyPagingItems()
    val keyword by viewModel.searchKeyword.collectAsState()
    val showImageSearchResultUi by viewModel.showImageSearchResultUi.collectAsState()

    SearchScreen(
        pagedImageSearchResult = pagedImageSearchResult,
        keyword = keyword,
        showImageSearchResultUi = showImageSearchResultUi,
        onKeywordChanged = { changedKeyword ->
            viewModel.handleUserAction(userAction = SearchScreenUserAction.ChangeSearchKeyword(changedKeyword))
        },
        onClickClearKeyword = {
            viewModel.handleUserAction(userAction = SearchScreenUserAction.ClearSearchKeyword)
        },
        onClickBookmark = { imageUi ->
            viewModel.handleUserAction(userAction = SearchScreenUserAction.ClickBookmark(imageUi = imageUi))
        }
    )
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    pagedImageSearchResult: LazyPagingItems<ImageUi>,
    keyword: String,
    showImageSearchResultUi: Boolean,
    onKeywordChanged: (String) -> Unit,
    onClickClearKeyword: () -> Unit,
    onClickBookmark: (ImageUi) -> Unit
) {
    Scaffold(
        topBar = {
            LezhinAssignMainTopAppBar(
                title = stringResource(id = R.string.screen_title_search),
                modifier = Modifier.fillMaxWidth()
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .fillMaxSize()
        ) {
            LezhinAssignmentTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimen.defaultSidePadding)
                    .padding(
                        top = Dimen.defaultLayoutTopPadding,
                        bottom = Dimen.defaultLayoutBottomPadding
                    ),
                keyword = keyword,
                hint = stringResource(id = R.string.common_search_text_field_hint),
                onKeywordChanged = onKeywordChanged,
                onClickClearKeyword = onClickClearKeyword
            )

            if (showImageSearchResultUi) {
                when (pagedImageSearchResult.loadState.refresh) {
                    is LoadState.Loading -> {
                        LezhinAssignmentCircularProgressIndicator(modifier = Modifier.fillMaxSize())
                    }

                    is LoadState.Error -> {
                        LezhinAssignmentErrorGuide(modifier = Modifier.fillMaxSize())
                    }

                    is LoadState.NotLoading -> {
                        if (pagedImageSearchResult.itemCount == 0) {
                            LezhinAssignmentNormalTextGuide(
                                modifier = Modifier.fillMaxSize(),
                                title = stringResource(id = R.string.search_empty_message)
                            )
                        } else {
                            LezhinLazyVerticalStaggeredGrid(
                                modifier = Modifier.fillMaxSize(),
                                content = {
                                    items(pagedImageSearchResult.itemCount) { index ->
                                        pagedImageSearchResult[index]?.let { item ->
                                            ImageSearchResultListItem(
                                                imageUi = item,
                                                onClickBookmark = { onClickBookmark(item) }
                                            )
                                        }
                                    }

                                    if (pagedImageSearchResult.loadState.append is LoadState.Error) {
                                        // 버튼으로 만들어서 재시도 로직을 추가할 수 있다.
                                        item(span = StaggeredGridItemSpan.FullLine) {
                                            LezhinAssignmentErrorGuide(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(100.dp)
                                            )
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            } else {
                LezhinAssignmentNormalTextGuide(
                    modifier = Modifier.fillMaxSize(),
                    title = stringResource(id = R.string.search_please_input_keyword)
                )
            }
        }
    }
}

@ThemePreviews
@DevicePreviews
@Preview
@Composable
fun SearchScreenPreview() {
    val lazyPagingItems = flowOf(PagingData.empty<ImageUi>()).collectAsLazyPagingItems()

    LezhinAssignmentTheme {
        SearchScreen(
            pagedImageSearchResult = lazyPagingItems,
            keyword = "Sample Keyword",
            showImageSearchResultUi = true,
            onKeywordChanged = {},
            onClickClearKeyword = {},
            onClickBookmark = {}
        )
    }
}
