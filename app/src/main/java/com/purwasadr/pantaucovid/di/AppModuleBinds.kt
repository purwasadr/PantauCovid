package com.purwasadr.pantaucovid.di

import com.purwasadr.pantaucovid.data.AppRepository
import com.purwasadr.pantaucovid.data.IAppRepository
import com.purwasadr.pantaucovid.data.source.local.ILocalDataSource
import com.purwasadr.pantaucovid.data.source.local.LocalDataSource
import com.purwasadr.pantaucovid.data.source.remote.IRemoteDataSource
import com.purwasadr.pantaucovid.data.source.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModuleBinds {

    @Singleton
    @Binds
    abstract fun provideAppRepository(
        appRepository: AppRepository
    ): IAppRepository

    @Singleton
    @Binds
    abstract fun provideRemoteDataSource(
        remoteDataSource: RemoteDataSource
    ): IRemoteDataSource

    @Singleton
    @Binds
    abstract fun provideLocalDataSource(
        localDataSource: LocalDataSource
    ): ILocalDataSource
}