package com.handson.thankapolo.di

import com.handson.data.remote.GamsagwaLoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideGamsagwaLoginService(retrofit: Retrofit) : GamsagwaLoginService{
        return retrofit.create(GamsagwaLoginService::class.java)
    }
}