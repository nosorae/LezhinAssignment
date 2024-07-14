package com.yessorae.presentation.screen.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.yessorae.presentation.R
import com.yessorae.presentation.common.component.LezhinAssignMainTopAppBar
import com.yessorae.presentation.common.component.LezhinAssignmentCircularProgressIndicator
import com.yessorae.presentation.common.component.LezhinAssignmentErrorGuide
import com.yessorae.presentation.common.component.LezhinAssignmentNormalTextGuide
import com.yessorae.presentation.common.component.LezhinAssignmentTextField
import com.yessorae.presentation.common.component.LezhinLazyVerticalStaggeredGrid
import com.yessorae.presentation.screen.bookmark.component.BookmarkListItem
import com.yessorae.presentation.screen.bookmark.component.SelectableBookmarkListItem
import com.yessorae.presentation.screen.bookmark.model.BookmarkImageUi
import com.yessorae.presentation.screen.bookmark.model.BookmarkScreenState
import com.yessorae.presentation.screen.bookmark.model.BookmarkScreenUserAction
import com.yessorae.presentation.screen.bookmark.model.SelectableBookmarkImageUi
import com.yessorae.presentation.theme.Dimen

@Composable
fun BookmarkScreenRoute(
    viewModel: BookmarkViewModel = hiltViewModel()
) {
    val screenState by viewModel.screenState.collectAsState()
    val keyword by viewModel.searchKeyword.collectAsState()

    BookmarkScreen(
        screenState = screenState,
        keyword = keyword,
        onKeywordChanged = { changedKeyword ->
            viewModel.handleUserAction(
                userAction = BookmarkScreenUserAction.ChangeSearchKeyword(changedKeyword)
            )
        },
        onClickClearKeyword = {
            viewModel.handleUserAction(
                userAction = BookmarkScreenUserAction.ClearSearchKeyword
            )
        },
        onClickDelete = { bookmarkImageUi ->
            viewModel.handleUserAction(
                userAction = BookmarkScreenUserAction.ClickDeleteSingleBookmark(bookmarkImageUi)
            )
        },
        onClickSelectableBookmarkImageUi = { selectableBookmarkImageUi ->
            viewModel.handleUserAction(
                userAction = BookmarkScreenUserAction.ClickBookmarkImageForMultipleDelete(selectableBookmarkImageUi)
            )
        }
    )
}

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    screenState: BookmarkScreenState,
    keyword: String,
    onKeywordChanged: (String) -> Unit,
    onClickClearKeyword: () -> Unit,
    onClickDelete: (BookmarkImageUi) -> Unit,
    onClickSelectableBookmarkImageUi: (SelectableBookmarkImageUi) -> Unit

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

            when (screenState) {
                is BookmarkScreenState.Loading -> {
                    LezhinAssignmentCircularProgressIndicator(modifier = Modifier.fillMaxSize())
                }

                is BookmarkScreenState.Empty -> {
                    LezhinAssignmentNormalTextGuide(
                        modifier = Modifier.fillMaxSize(),
                        title = stringResource(id = R.string.bookmark_empty_message)
                    )
                }

                is BookmarkScreenState.Success -> {
                    LezhinLazyVerticalStaggeredGrid(
                        modifier = Modifier.fillMaxSize(),
                        content = {
                            items(screenState.bookmarkImages) { item ->
                                BookmarkListItem(
                                    bookmarkImageUi = item,
                                    onClickDelete = { onClickDelete(item) }
                                )
                            }
                        }
                    )
                }

                is BookmarkScreenState.Edit -> {
                    LezhinLazyVerticalStaggeredGrid(
                        modifier = Modifier.fillMaxSize(),
                        content = {
                            items(screenState.selectableBookmarkImages) { item ->
                                SelectableBookmarkListItem(
                                    selectableBookmarkImageUi = item,
                                    onClick = { onClickSelectableBookmarkImageUi(item) }
                                )
                            }
                        }
                    )
                }

                is BookmarkScreenState.Error -> {
                    LezhinAssignmentErrorGuide(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}