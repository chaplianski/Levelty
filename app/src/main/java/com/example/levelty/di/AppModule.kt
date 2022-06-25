package com.example.levelty.di

import android.content.Context
import androidx.room.Room
import com.example.levelty.data.repository.*
import com.example.levelty.data.storage.database.*
import com.example.levelty.data.storage.storage.CategoryStorage
import com.example.levelty.data.storage.storage.PurposeStorage
import com.example.levelty.data.storage.storage.RepeatStorage
import com.example.levelty.data.storage.storage.TaskStorage
import com.example.levelty.domain.repository.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule() {

    @Singleton
    @Provides
    fun provideLeveltyDao(leveltyDB: LeveltyDB) = leveltyDB.leveltyDao()

    @Singleton
    @Provides
    fun provideLeveltyDB(context: Context): LeveltyDB =
        Room.databaseBuilder(
            context,
            LeveltyDB::class.java,
            "levelty_db"
        )
            .build()
//
//    @Provides
//    fun provideBriefcaseStorage(impl: BriefCaseStorageImpl): BriefCaseStorage = impl
//
    @Provides
    fun provideKidRepository(impl: KidRepositoryImpl): KidRepository = impl
    @Provides
    fun provideGoalRepository(impl: GoalRepositoryImpl): GoalRepository = impl
    @Provides
    fun provideInterestRepository(impl: InterestRepositoryImpl): InterestRepository = impl
    @Provides
    fun providePurposeRepository(impl: PurposeRepositoryImpl): PurposeRepository = impl
    @Provides
    fun provideTaskRepository(impl: TaskRepositoryImpl): TaskRepository = impl
    @Provides
    fun provideRepeatRepository(impl: RepeatRepositoryImpl): RepeatRepository = impl
    @Provides
    fun provideCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository = impl



    @Provides
    fun provideBTaskStorage(impl: TaskStorageImpl): TaskStorage = impl
    @Provides
    fun provideRepeatStorage(impl: RepeatStorageImpl): RepeatStorage = impl
    @Provides
    fun provideCategoryStorage(impl: CategoryStorageImpl): CategoryStorage = impl
    @Provides
    fun providePurposeStorage(impl: PurposeStorageImpl): PurposeStorage = impl

}
