package com.purwasadr.pantaucovid.di

import com.purwasadr.pantaucovid.BuildConfig
import com.purwasadr.pantaucovid.data.source.remote.network.CovidRateService
import com.purwasadr.pantaucovid.data.source.remote.network.HospitalService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() =
        OkHttpClient
            .Builder()
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

    @Singleton
    @Provides
    fun provideApiService(client: OkHttpClient): CovidRateService =
        CovidRateService.create(client)

    @Singleton
    @Provides
    fun provideHospital(client: OkHttpClient): HospitalService =
        HospitalService.create(client)
}