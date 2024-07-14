package com.yessorae.presentation.screen.saerch.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.yessorae.presentation.screen.saerch.model.ImageUi

@Composable
fun ImageSearchResultListItem(
    modifier: Modifier = Modifier,
    imageUi: ImageUi
) {
    val context = LocalContext.current
    val aspectRatio = imageUi.width.toFloat() / imageUi.height.toFloat()

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(imageUi.thumbnailUrl)
            .crossfade(true)
            .scale(Scale.FILL)
            .build(),
        contentDescription = "Image",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(aspectRatio)
    )
}