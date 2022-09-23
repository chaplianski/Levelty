package com.example.levelty.data.network.service

import com.example.levelty.data.storage.model.ResponseGoalComplete
import com.squareup.moshi.Json
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface CompleteGoalApiService{
    @POST ("children/me/child-goal-purchases")
    suspend fun completeCoal(
        @Body sendData: RequestBody
    ): Response<ResponseGoalComplete>
}