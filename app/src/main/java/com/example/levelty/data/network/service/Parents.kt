package com.example.levelty.data.network.service

import com.example.levelty.data.storage.model.ChildrenItemDTO
import com.example.levelty.data.storage.model.CreatedTasksItemDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface GetChildrenApiService {
    @Headers("Content-Type: application/json")
    @GET("parents/me/children")
    suspend fun getKids(
//        @Header("Authorization") token: String
    ): Response<List<ChildrenItemDTO>>
//            Response <(String, List<ChildrenItemDTO>) -> List<ChildrenItemDTO>>
}

interface GetCurrentParentTasksApiService {
    @Headers("Content-Type: application/json")
    @GET("parents/me/tasks")
    suspend fun getKids(
        @Header("Authorization") token: String
    ): Response<List<CreatedTasksItemDTO>>
}