package com.example.levelty.data.network.retrofit

import com.example.levelty.R
import com.example.levelty.domain.exceptions.NetworkException
import okhttp3.Interceptor
import okhttp3.Response
//TODO либо пробовать делать колл адаптер как тут) https://www.youtube.com/watch?v=zUP-ECi37s0&t=393
class ErrorHandlerInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val error = when (response.code){
            in 300..399 -> {
                NetworkException(R.string.internet_error)
            }
            in 400..499 -> {
                NetworkException(R.string.client_error)
            }
            in 500..599 -> {
                NetworkException(R.string.server_error)
            }
            else -> null
        }
        return if (error == null)
            response
        else
            throw error
    }
}