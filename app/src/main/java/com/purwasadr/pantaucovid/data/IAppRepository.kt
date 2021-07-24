package com.purwasadr.pantaucovid.data

import com.purwasadr.pantaucovid.model.Covid
import kotlinx.coroutines.flow.Flow

interface IAppRepository {
    fun getCovidData(): Flow<Resource<Covid?>>

}