package com.yessorae.data.source.local.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yessorae.data.source.local.database.model.BookmarkImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkImageDao {
    @Query(
        """
            SELECT ${BookmarkImageEntity.COL_IMAGE_URL} FROM ${BookmarkImageEntity.TABLE_NAME}
        """
    )
    fun getAllBookmarkImageUrl(): Flow<List<String>>

    @Query(
        """
            SELECT * FROM ${BookmarkImageEntity.TABLE_NAME} 
            WHERE (:keyword = '') OR (${BookmarkImageEntity.KEYWORD} LIKE '%' || :keyword || '%')
        """
    )
    fun getBookmarkImagePagingSource(keyword: String): Flow<List<BookmarkImageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookmarkImageEntity: BookmarkImageEntity)

    @Query(
        """
            DELETE FROM ${BookmarkImageEntity.TABLE_NAME} WHERE ${BookmarkImageEntity.COL_IMAGE_URL} = :imageUrl
        """
    )
    suspend fun deleteByImageUrl(imageUrl: String): Int
}
