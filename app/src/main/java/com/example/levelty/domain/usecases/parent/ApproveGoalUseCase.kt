package com.example.levelty.domain.usecases.parent

import com.example.levelty.R
import com.example.levelty.domain.exceptions.InternetConnectionException
import com.example.levelty.domain.exceptions.NetworkException
import com.example.levelty.domain.models.GoalsItem
import com.example.levelty.domain.repository.GoalRepository
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class ApproveGoalUseCase @Inject constructor(private val goalRepository: GoalRepository) {

    suspend fun execute(goalId: Int, goalPrice: Int): Result<GoalsItem> {
        return Result.runCatching {
            try {
                goalRepository.approveGoal(goalId, goalPrice)
            } catch (e: IOException) {
                throw  InternetConnectionException(R.string.internet_error)
            } catch (e: UnknownHostException) {
                throw  NetworkException(R.string.server_error)
            } catch (e: ConnectException) {
                throw  InternetConnectionException(R.string.client_error)
            }
        }
    }
}