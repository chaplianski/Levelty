package com.example.levelty.data.network.service

import com.example.levelty.data.storage.model.CreatedTasksItemDTO
import com.example.levelty.data.storage.response.GoalResponse
import com.example.levelty.data.storage.response.ParentResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface CreateNewTaskApiService {
    //    @Headers("Content-Type: application/json")
    @POST ("parents/me/tasks")
    suspend fun createTask(
        @Body newTaskDTO: RequestBody
    ): Response<CreatedTasksItemDTO>
}

interface GetChildrenApiService {
//    @Headers("Content-Type: application/json")
    @GET("parents/me/children")
    suspend fun getKids(
//        @Header("Authorization") token: String
    ): Response<ParentResponse>
//            Response <(String, List<ChildrenItemDTO>) -> List<ChildrenItemDTO>>
}

interface ReviewChildGoalApiService {
    @PUT("parents/me/children/child-goals/{goal_id}")
    suspend fun approveGoal(
        @Path("goal_id") goalId: Int,
        goalPrice: Int
    ): Response<GoalResponse>
}

interface UpdateChoreStatusApiService {
    @PATCH("children/me/chores/{chore_id}/status")
    suspend fun updateChore(
        @Path("chore_id") choreId: Int
    ): Response<CreatedTasksItemDTO> //TODO уточнить что приходит с сервера
}

interface UpdateParenTaskApiService {
    //    @Headers("Content-Type: application/json")
    @PUT("parents/me/tasks/{task_id}")
    suspend fun updateTask(
        @Path("task_id") taskId: Int, @Body newTaskDTO: RequestBody
    ) : Response<CreatedTasksItemDTO>
}

interface UpdateTaskStatusApiService {
//    @Headers("Content-Type: application/json")
    @PATCH("parents/me/tasks/{task_id}/status/{task_status}")
    suspend fun updateTaskStatus(
        @Path("task_id") taskId: String, @Path("task_status") status: String
    ): Response<CreatedTasksItemDTO>
}





