package com.purwasadr.pantaucovid.data.source.remote.network

import com.purwasadr.pantaucovid.BuildConfig
import com.purwasadr.pantaucovid.data.source.remote.response.CovidResponse
import com.purwasadr.pantaucovid.data.source.remote.response.ProvinceResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface HospitalService {
    @GET("api/get-provinces")
    suspend fun getProvince(): ProvinceResponse

    companion object {
        fun create(): ApiService {
            val client = OkHttpClient.Builder()
                .also {
                    if (BuildConfig.DEBUG) {
                        it.addInterceptor(
                            HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BODY)
                        )
                    }
                }
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(" https://rs-bed-covid-api.vercel.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ApiService::class.java)
        }
    }
}