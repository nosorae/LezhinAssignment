package com.yessorae.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yessorae.data.source.local.database.dao.BookmarkImageDao
import com.yessorae.data.source.local.database.model.BookmarkImageEntity

@Database(
    entities = [BookmarkImageEntity::class],
    version = 1
)
abstract class LezhinAssignmentDatabase : RoomDatabase() {
    abstract fun getBookmarkImageDao(): BookmarkImageDao

    companion object {
        const val NAME = "lezhin-assignment-database"
    }
}
