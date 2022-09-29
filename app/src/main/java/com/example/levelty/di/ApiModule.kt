package com.example.levelty.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.levelty.data.network.retrofit.BASE_URL
import com.example.levelty.data.network.retrofit.ErrorHandlerInterceptor
import com.example.levelty.data.network.retrofit.PARENT_TOKEN
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class ApiModule {

    private var context: Context? = null

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return moshi
    }

    @Provides
    @Singleton
    fun provideInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideErrorHandlerInterceptor() = ErrorHandlerInterceptor()

    @Provides
    @Singleton
    fun provideOkhttpClient(
        interceptor: HttpLoggingInterceptor,
        errorHandlerInterceptor:ErrorHandlerInterceptor
    ): OkHttpClient {
        val okkHttpclient = OkHttpClient.Builder()
            .cache(provideCache())
//            .addInterceptor(provideOfflineCacheInterceptor())
//            .addNetworkInterceptor(provideCacheInterceptor())
//            .addInterceptor(interceptor)
            .addInterceptor {chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer $PARENT_TOKEN")
                    .build()
                chain.proceed(request)  }
            .addNetworkInterceptor(interceptor)
            .addInterceptor(errorHandlerInterceptor)
            .build()
        return okkHttpclient
    }



    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        return retrofit
    }

    @Provides
    @Singleton
    fun provideCache(): Cache? {
        var cache: Cache? = null
        try {
            cache = Cache(
                File(context?.getCacheDir(), "http-cache"),
                10 * 1024 * 1024
            ) // 10 MB
        } catch (e: Exception) {
        }
        return cache
    }

    @Provides
    @Singleton
    fun provideCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response: Response = chain.proceed(chain.request())
            val cacheControl: CacheControl = CacheControl.Builder()
                .maxAge(5, TimeUnit.MINUTES)
                .build()
            val request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer $PARENT_TOKEN")
                .header("Cache-Control", "public, max-age= $cacheControl")
                .build()
            chain.proceed(request)


            // re-write response header to force use of cache

            response.newBuilder()

                .build()
        }
    }


//    internal class RequestCacheInterceptor : Interceptor {
//        @Throws(IOException::class)
//        override fun intercept(chain: Interceptor.Chain): Response {
//            val builder: Request.Builder =
//                chain.request().NewBuilder() // Изменить на основе исходного запроса
////            if (is) {
//                // Нет автономного принудительного кэша
//                builder.cacheControl(CacheControl.FORCE_CACHE) // Эквивалентно добавлению только if-cache
////            }
//            val newRequest = builder.build()
//            return chain.proceed(newRequest)
//        }
//    }

    @Provides
    @Singleton
    fun provideOfflineCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request: Request = chain.request()
            if (context?.let { isNetworkAvailable(it) } == false) {
//                val cacheControl: CacheControl = Builder()
//                    .maxStale(7, TimeUnit.DAYS)
//                    .build()
                request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7)
//                    .cacheControl(cacheControl)
                    .build()
            }
            chain.proceed(request)
        }
    }

    fun isNetworkAvailable(context: Context): Boolean? {
        var isConnected: Boolean? = false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }
}