package com.yessorae.presentation.common.component

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.yessorae.presentation.theme.LightGray

@Composable
fun LezhinAssignmentImage(
    imageUrl: String,
    thumbnailUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    var currentUrl by remember(imageUrl) { mutableStateOf(imageUrl) }

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(currentUrl)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier.background(color = LightGray),
        onError = {
            if (imageUrl != thumbnailUrl && currentUrl == imageUrl) {
                currentUrl = thumbnailUrl
            }
        }
    )
}
