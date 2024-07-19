package com.yessorae.presentation.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yessorae.presentation.common.util.DevicePreviews
import com.yessorae.presentation.common.util.ThemePreviews
import com.yessorae.presentation.theme.Dimen
import kotlin.random.Random

@Composable
fun LezhinLazyVerticalStaggeredGrid(
    modifier: Modifier = Modifier,
    content: LazyStaggeredGridScope.() -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(
            start = Dimen.defaultSidePadding,
            end = Dimen.defaultSidePadding,
            bottom = 30.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(Dimen.defaultListItemPadding),
        verticalItemSpacing = Dimen.defaultListItemPadding,
        modifier = modifier,
        content = content
    )
}

@ThemePreviews
@DevicePreviews
@Preview
@Composable
fun LezhinLazyVerticalStaggeredGridPreview() {
    LezhinLazyVerticalStaggeredGrid {
        items(50) { index ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp + (Random.nextInt(until = 10) * 10).dp)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text("Item $index")
            }
        }
    }
}
