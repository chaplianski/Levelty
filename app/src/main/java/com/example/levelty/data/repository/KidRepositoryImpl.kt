package com.example.levelty.data.repository

import com.example.levelty.data.network.retrofit.parents.GetChildrenApiHelper
import com.example.levelty.data.storage.database.KidStorageImpl
import com.example.levelty.domain.models.Child
import com.example.levelty.domain.models.ChildrenItem
import com.example.levelty.domain.repository.KidRepository
import javax.inject.Inject

class KidRepositoryImpl @Inject constructor(
    private val kidStorageImpl: KidStorageImpl,
    private val getChildrenApiHelper: GetChildrenApiHelper
    ): KidRepository {

    override suspend fun getKids(): List<ChildrenItem> {
//    return
//    return getChildrenApiHelper.getChildren().map { it.childrenMapDataToDomain() } // TODO switch to get data from server
        return kidStorageImpl.getKids().map { it.childrenMapDataToDomain() }
    }

    override fun getChild(): Child {
        return com.example.levelty.data.utils.childMapDomainToData()
    }



    override fun getKid(kidId: Int): ChildrenItem {
        return kidStorageImpl.getKid(kidId).childrenMapDataToDomain()
    }
}