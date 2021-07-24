package com.purwasadr.pantaucovid.di

import com.purwasadr.pantaucovid.data.source.remote.network.ApiService
import com.purwasadr.pantaucovid.data.source.remote.network.HospitalService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideApiService(): ApiService = ApiService.create()

    @Singleton
    @Provides
    fun provideHospital(): HospitalService = HospitalService.create()
}