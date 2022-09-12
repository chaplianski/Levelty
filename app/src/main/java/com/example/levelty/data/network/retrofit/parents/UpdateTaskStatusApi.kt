package com.example.levelty.data.network.retrofit.parents

import android.util.Log
import com.example.levelty.R
import com.example.levelty.data.network.service.UpdateTaskStatusApiService
import com.example.levelty.data.storage.model.CreatedTasksItemDTO
import com.example.levelty.domain.exceptions.NetworkException
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class UpdateTaskStatusApiHelper @Inject constructor(){

    @Inject
    lateinit var updateTaskStatusRetrofit: Retrofit

    suspend fun updateTask(taskId: Int, status: String): String{
        val retrofit = updateTaskStatusRetrofit.create(UpdateTaskStatusApiService::class.java)
        var result = ""
        Log.d("MyLog", "taskId = $taskId, status = $status")
        val response = retrofit.updateTaskStatus(taskId.toString(), status)
        Log.d("MyLog", "response code = ${response.code()}")
        when (response.code()){
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