package com.example.levelty.data.network.retrofit.kid

import com.example.levelty.R
import com.example.levelty.data.network.service.UpdateChoreStatusApiService
import com.example.levelty.domain.exceptions.NetworkException
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class UpdateChoreStatusApiHelper @Inject constructor() {

    @Inject
    lateinit var updateChareStatusRetrofit: Retrofit

    suspend fun updateChoreStatus(choreId: Int): String {

    val retrofit = updateChareStatusRetrofit.create(UpdateChoreStatusApiService::class.java)

        var result = ""
        val response = retrofit.updateChore(choreId)

        when(response.code()){
            in 200..299 -> {
                result = "Status update successfully"
            }
            in 300..399 -> {
                throw NetworkException(R.string.internet_error)
            }
            in 400..499 -> {
                throw NetworkException(R.string.client_error)
            }
            in 500..599 -> {
                throw NetworkException(R.string.server_error)
            }
        }
        return result
    }
}