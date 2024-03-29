package com.example.rafaelanastacioalves.moby.repository.http


import com.example.rafaelanastacioalves.moby.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {

    fun <S> createService(serviceClass: Class<S>): S {
        val httpClient = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE

        val builder = Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        val retrofit = builder.client(
            httpClient
                .addInterceptor(interceptor)
                .build()
        ).build()
        return retrofit.create(serviceClass)
    }

}
