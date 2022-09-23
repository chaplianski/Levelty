package com.example.levelty.domain.usecases.parent

import com.example.levelty.R
import com.example.levelty.domain.exceptions.InternetConnectionException
import com.example.levelty.domain.exceptions.NetworkException
import com.example.levelty.domain.models.ChildrenItem
import com.example.levelty.domain.models.Kid
import com.example.levelty.domain.repository.KidRepository
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class GetKidsUseCase @Inject constructor(private val kidRepository: KidRepository) {

        suspend fun execute(): List<ChildrenItem> {
        return kidRepository.getKids()

//    suspend fun execute(): Result<List<ChildrenItem>> {
//        return Result.runCatching {
//            try {
//                kidRepository.getKids()
//            } catch (e: IOException) {
//                throw  InternetConnectionException(R.string.internet_error)
//            } catch (e: UnknownHostException) {
//                throw  NetworkException(R.string.server_error)
//            } catch (e: ConnectException) {
//                throw  InternetConnectionException(R.string.client_error)
//            }
//        }
    }

}