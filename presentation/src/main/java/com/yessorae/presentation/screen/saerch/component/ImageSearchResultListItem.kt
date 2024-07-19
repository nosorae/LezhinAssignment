package com.yessorae.presentation.screen.saerch.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.yessorae.presentation.R
import com.yessorae.presentation.common.component.LezhinAssignmentImage
import com.yessorae.presentation.common.util.DevicePreviews
import com.yessorae.presentation.common.util.ThemePreviews
import com.yessorae.presentation.screen.saerch.model.ClickData
import com.yessorae.presentation.screen.saerch.model.ImageUi
import com.yessorae.presentation.theme.LezhinAssignmentTheme

@Composable
fun ImageSearchResultListItem(
    modifier: Modifier = Modifier,
    imageUi: ImageUi,
    onClickBookmark: () -> Unit
) {
    val aspectRatio = imageUi.width.toFloat() / imageUi.height.toFloat()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(aspectRatio)
    ) {
        LezhinAssignmentImage(
            imageUrl = imageUi.imageUrl,
            thumbnailUrl = imageUi.thumbnailUrl,
            modifier = Modifier.fillMaxSize(),
            contentDescription = stringResource(id = R.string.content_description_image_search_result)
        )

        IconButton(
            onClick = onClickBookmark,
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = stringResource(id = R.string.content_description_add_bookmark),
                tint = if (imageUi.isBookmark) Color.Yellow else Color.LightGray.copy(alpha = 0.9f)
            )
        }
    }
}

@ThemePreviews
@DevicePreviews
@Preview
@Composable
fun ImageSearchResultListItemPreview() {
    val sampleImageUi = ImageUi(
        imageUrl = "https://example.com/image.jpg",
        thumbnailUrl = "https://example.com/thumbnail.jpg",
        width = 200,
        height = 300,
        isBookmark = true,
        clickData = ClickData(keyword = "")
    )

    LezhinAssignmentTheme {
        ImageSearchResultListItem(
            imageUi = sampleImageUi,
            onClickBookmark = {}
        )
    }
}
