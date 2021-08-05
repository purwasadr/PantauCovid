package com.purwasadr.pantaucovid.data.source.remote.network

import com.purwasadr.pantaucovid.data.source.remote.response.CovidResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CovidRateService {

    @GET("/public/api/update.json")
    suspend fun getCovidRate(): CovidResponse

    companion object {
        fun create(client: OkHttpClient): CovidRateService {

            return Retrofit.Builder()
                .baseUrl("https://data.covid19.go.id/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(CovidRateService::class.java)
        }
    }
}