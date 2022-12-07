package com.handson.thankapolo.di

import com.handson.thankapolo.BuildConfig.BASE_URL
import com.handson.thankapolo.utils.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    // 감사과 로그인 전용 API 레트로핏
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class GamsagwaLoginRetrofit

    // 감사과 전용 API 레트로핏
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class GamsagwaRetrofit

    @Provides
    @GamsagwaLoginRetrofit
    @Singleton
    fun provideLoginRetrofit(gsonConverterFactory: GsonConverterFactory, @GamsagwaLoginRetrofit client: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()
    }

    @Provides
    @GamsagwaRetrofit
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory, @GamsagwaRetrofit client: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory() : GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    @GamsagwaLoginRetrofit
    fun provideLoginHttpClient(loggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @GamsagwaRetrofit
    fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor, authInterceptor: AuthInterceptor) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()
    }


    @Provides
    @Singleton
    fun provideAuthInterceptor(authInterceptor: AuthInterceptor) : Interceptor = authInterceptor

}