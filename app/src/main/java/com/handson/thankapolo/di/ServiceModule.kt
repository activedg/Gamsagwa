package com.handson.thankapolo.di

import com.handson.data.remote.GamsagwaLoginService
import com.handson.data.remote.GamsagwaService
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
    fun provideGamsagwaLoginService(@NetworkModule.GamsagwaLoginRetrofit retrofit: Retrofit) : GamsagwaLoginService{
        return retrofit.create(GamsagwaLoginService::class.java)
    }

    @Provides
    @Singleton
    fun provideGamsagwaService(@NetworkModule.GamsagwaRetrofit retrofit: Retrofit) : GamsagwaService{
        return retrofit.create(GamsagwaService::class.java)
    }
}