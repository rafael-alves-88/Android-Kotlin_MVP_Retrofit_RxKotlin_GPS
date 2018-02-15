package com.rafael.alves.sunsettimes.repository.api

import com.rafael.alves.sunsettimes.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

open class BaseAPI {

    fun getRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(BuildConfig.RETROFIT_TIMEOUT, TimeUnit.SECONDS)

        // Interceptor
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Timber.tag("RETROFIT L").d(message) })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(interceptor)
        httpClient.addNetworkInterceptor { chain ->
            val request = chain.request()
            Timber.tag("RETROFIT HEADERS").d(request.headers().toString())
            Timber.tag("RETROFIT BODY").d(request.body().toString())
            chain.proceed(request)
        }

        // Client
        val interceptorClient = httpClient.build()

        // Builder
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(interceptorClient)
                .baseUrl(BuildConfig.API_BASE_URL)
                .build()
    }
}