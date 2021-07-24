package com.purwasadr.pantaucovid.data.source.remote.network

import com.purwasadr.pantaucovid.data.source.remote.response.CovidDataResponse
import com.purwasadr.pantaucovid.data.source.remote.response.CovidResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("https://data.covid19.go.id/public/api/update.json")
    suspend fun getCovidData(): CovidResponse
}