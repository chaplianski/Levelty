package com.example.levelty.data.network.retrofit.kid

import com.example.levelty.R
import com.example.levelty.data.network.service.CompleteGoalApiService
import com.example.levelty.domain.exceptions.NetworkException
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Retrofit
import javax.inject.Inject

class CompleteGoalApiHelper @Inject constructor(){

    @Inject
    lateinit var completeGoalRetrofit: Retrofit

    suspend fun goalComplete(goalId: Int): String {

        val retrofit = completeGoalRetrofit.create(CompleteGoalApiService::class.java)

        var result = ""
        val json = JSONObject()
        json.put("child_goal_id", goalId)
        val sendData = json.toString().toRequestBody("application/json".toMediaTypeOrNull())
        val response = retrofit.completeCoal(sendData)

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