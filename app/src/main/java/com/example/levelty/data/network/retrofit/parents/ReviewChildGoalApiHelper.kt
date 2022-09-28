package com.example.levelty.data.network.retrofit.parents

import android.content.Context
import android.util.Log
import com.example.levelty.R
import com.example.levelty.data.network.service.GetChildrenApiService
import com.example.levelty.data.network.service.ReviewChildGoalApiService
import com.example.levelty.data.storage.model.ChildrenItemDTO
import com.example.levelty.data.storage.model.GoalsItemDTO
import com.example.levelty.domain.exceptions.NetworkException
import retrofit2.Retrofit
import javax.inject.Inject

class ReviewChildGoalApiHelper @Inject constructor() {

    @Inject
    lateinit var approveGoalRetrofit: Retrofit

    @Inject
    lateinit var context: Context

    suspend fun parentApproveGoal(goalId: Int, goalPrice: Int): GoalsItemDTO {
        val retrofit = approveGoalRetrofit.create(ReviewChildGoalApiService::class.java)

        var goal = GoalsItemDTO(0,0,"","",0,"","")

        val responseGoal = retrofit.approveGoal(goalId, goalPrice)//("Bearer $PARENT_TOKEN")

        when (responseGoal.code()) {
            in 200..299 -> {
                goal = responseGoal.body()?.data ?: GoalsItemDTO(0,0,"","",0,"","")
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
        return goal


    }
}