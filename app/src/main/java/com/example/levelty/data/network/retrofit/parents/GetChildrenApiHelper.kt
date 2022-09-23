package com.example.levelty.data.network.retrofit.parents

import android.content.Context
import android.util.Log
import com.example.levelty.R
import com.example.levelty.data.network.service.GetChildrenApiService
import com.example.levelty.data.storage.model.ChildrenItemDTO
import com.example.levelty.domain.exceptions.NetworkException
import retrofit2.Retrofit
import javax.inject.Inject

class GetChildrenApiHelper @Inject constructor() {

    @Inject
    lateinit var getChildrenRetrofit: Retrofit

    @Inject
    lateinit var context: Context

    suspend fun getChildren(): List<ChildrenItemDTO?>{
        val retrofit = getChildrenRetrofit.create(GetChildrenApiService::class.java)

        var kidList: List<ChildrenItemDTO?> = emptyList()
//        val json =JSONObject()
//        json.put("token", PARENT_TOKEN)
//        val sendData = json.toString().toRequestBody("application/json".toMediaTypeOrNull())

        val responseKids = retrofit.getKids()//("Bearer $PARENT_TOKEN")

        when (responseKids.code()) {
            in 200..299 -> {
                kidList = (responseKids.body()?.data?.children ?: emptyList()) //as List<ChildrenItemDTO>

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
        Log.d("MyLog", "response kidList= $kidList")
        return kidList


    }
}