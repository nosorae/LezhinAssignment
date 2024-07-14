package com.yessorae.data.di

import android.content.Context
import androidx.room.Room
import com.yessorae.data.source.local.database.LezhinAssignmentDatabase
import com.yessorae.data.source.local.database.dao.BookmarkImageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesLezhinAssignmentDatabase(@ApplicationContext context: Context): LezhinAssignmentDatabase =
        Room.databaseBuilder(
            context = context,
            klass = LezhinAssignmentDatabase::class.java,
            name = LezhinAssignmentDatabase.NAME
        ).build()

    @Provides
    fun provideBookmarkImageDao(database: LezhinAssignmentDatabase): BookmarkImageDao = database.getBookmarkImageDao()
}
