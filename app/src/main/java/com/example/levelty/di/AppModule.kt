package com.example.levelty.di

import android.content.Context
import com.example.levelty.data.repository.*
import com.example.levelty.data.storage.database.TaskStorageImpl
import com.example.levelty.data.storage.storage.TaskStorage
import com.example.levelty.domain.repository.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule() {

//    @Singleton
//    @Provides
//    fun provideBriefCaseDao(briefCaseDB: BriefCaseDB) = briefCaseDB.BriefCaseDao()
//
//    @Singleton
//    @Provides
//    fun provideBriefCaseDB(context: Context): BriefCaseDB =
//        Room.databaseBuilder(
//            context,
//            BriefCaseDB::class.java,
//            "briefcase_db"
//        )
//            //          .fallbackToDestructiveMigration()
//            .build()
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
    fun provideBTaskStorage(impl: TaskStorageImpl): TaskStorage = impl

}
