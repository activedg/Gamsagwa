package com.handson.thankapolo.di

import com.handson.data.remote.GamsagwaLoginService
import com.handson.data.remote.GamsagwaService
import com.handson.data.source.HomeRepositoryImpl
import com.handson.data.source.LoginRepositoryImpl
import com.handson.data.source.ProfileRepositoryImpl
import com.handson.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.handson.domain.repository.LoginRepository
import com.handson.domain.repository.ProfileRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun providesLoginRepository(gamsagwaLoginService: GamsagwaLoginService) : LoginRepository {
        return LoginRepositoryImpl(gamsagwaLoginService)
    }

    @Singleton
    @Provides
    fun providesHomeRepository(gamsagwaService: GamsagwaService) : HomeRepository{
        return HomeRepositoryImpl(gamsagwaService)
    }

    @Singleton
    @Provides
    fun providesProfileRepository(gamsagwaService: GamsagwaService) : ProfileRepository{
        return ProfileRepositoryImpl(gamsagwaService)
    }
}