package com.yessorae.data.source.local.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yessorae.domain.entity.SearchedImage

@Entity(tableName = BookmarkImageEntity.TABLE_NAME)
data class BookmarkImageEntity(
    @ColumnInfo(name = COL_THUMBNAIL_URL)
    val thumbnailUrl: String,
    @PrimaryKey
    @ColumnInfo(name = COL_IMAGE_URL)
    val imageUrl: String,
    @ColumnInfo(name = COL_WIDTH)
    val width: Int,
    @ColumnInfo(name = COL_HEIGHT)
    val height: Int,
    @ColumnInfo(name = KEYWORD)
    val keyword: String
) {
    companion object {
        const val TABLE_NAME = "bookmark_image_table"
        const val COL_THUMBNAIL_URL = "thumbnail_url"
        const val COL_IMAGE_URL = "image_url"
        const val COL_WIDTH = "width"
        const val COL_HEIGHT = "height"
        const val KEYWORD = "keyword"
    }
}

fun BookmarkImageEntity.asDomainModel() =
    SearchedImage(
        thumbnailUrl = thumbnailUrl,
        imageUrl = imageUrl,
        width = width,
        height = height,
        keyword = keyword
    )

fun SearchedImage.asEntity() =
    BookmarkImageEntity(
        thumbnailUrl = thumbnailUrl,
        imageUrl = imageUrl,
        width = width,
        height = height,
        keyword = keyword
    )
