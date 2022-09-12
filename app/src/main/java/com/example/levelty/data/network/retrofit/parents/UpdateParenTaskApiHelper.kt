package com.example.levelty.data.network.retrofit.parents

import com.example.levelty.R
import com.example.levelty.data.network.service.UpdateParenTaskApiService
import com.example.levelty.data.storage.model.NewTaskDTO
import com.example.levelty.domain.exceptions.NetworkException
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Retrofit
import javax.inject.Inject

class UpdateParenTaskApiHelper @Inject constructor(){

    @Inject
    lateinit var updateParentTaskRetrofit: Retrofit

    suspend fun updateTask(taskID: Int, newTaskDTO: NewTaskDTO): String{

        val retrofit = updateParentTaskRetrofit.create(UpdateParenTaskApiService::class.java)
        var result = ""
        val taskToJson = Gson().toJson(newTaskDTO)
        val response = retrofit.updateTask(taskID, taskToJson.toRequestBody("application/json".toMediaTypeOrNull()))

        when (response.code()){
            in 200..299 -> {
                result = "Task update successfully"
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