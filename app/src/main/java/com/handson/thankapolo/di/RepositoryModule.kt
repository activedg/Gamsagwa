package com.handson.thankapolo.di

import com.handson.data.remote.GamsagwaLoginService
import com.handson.data.source.LoginRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.handson.domain.data.login.repository.LoginRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun providesLoginRepository(gamsagwaLoginService: GamsagwaLoginService) : LoginRepository {
        return LoginRepositoryImpl(gamsagwaLoginService)
    }
}