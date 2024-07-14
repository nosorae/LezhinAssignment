package com.yessorae.domain.respository

import com.yessorae.domain.entity.BookmarkImage


interface BookmarkImageRepository {
    suspend fun getImage(imageUrl: String): BookmarkImage
    suspend fun updateImageBookmark(imageUrl: String, isBookmark: Boolean)
}