package com.example.levelty.data.network.retrofit.parents

import com.example.levelty.R
import com.example.levelty.data.network.service.CreateNewTaskApiService
import com.example.levelty.data.storage.model.NewTaskDTO
import com.example.levelty.domain.exceptions.NetworkException
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Retrofit
import javax.inject.Inject

class CreateNewTaskApiHelper @Inject constructor() {

    @Inject
    lateinit var createNewTaskRetrofit: Retrofit

    suspend fun createNewTask(newTaskDTO: NewTaskDTO): String{

        val retrofit = createNewTaskRetrofit.create(CreateNewTaskApiService::class.java)

        val newTaskJson = Gson().toJson(newTaskDTO)
        var result = ""
        val response = retrofit.createTask(newTaskJson.toRequestBody("application/json".toMediaTypeOrNull()))

        when (response.code()){
            in 200..299 -> {
                result = "Status update successfully"
            }
            in 300..399 -> {
                throw NetworkException(R.string.internet_error)
            }xx
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