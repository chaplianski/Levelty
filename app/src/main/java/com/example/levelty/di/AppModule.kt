package com.example.levelty.di

import android.content.Context
import com.example.levelty.data.repository.KidRepositoryImpl
import com.example.levelty.domain.repository.KidRepository
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

}
