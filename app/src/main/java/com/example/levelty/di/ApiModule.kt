package com.example.levelty.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.levelty.data.network.retrofit.BASE_URL
import com.example.levelty.data.network.retrofit.PARENT_TOKEN
import com.example.levelty.di.ApiModule_ProvideCacheFactory.provideCache
import com.example.levelty.di.ApiModule_ProvideCacheInterceptorFactory.provideCacheInterceptor
import com.example.levelty.di.ApiModule_ProvideOfflineCacheInterceptorFactory.provideOfflineCacheInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import javax.inject.Inject
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
    @Singleton
    fun provideOkhttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
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

            // re-write response header to force use of cache
//            val cacheControl: CacheControl = Builder()
//                .maxAge(5, TimeUnit.MINUTES)
//                .build()
            response.newBuilder()
                .header("Cache-Control", "public, max-age=" +5) //cacheControl.toString())

                .build()
        }
    }

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
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }
}