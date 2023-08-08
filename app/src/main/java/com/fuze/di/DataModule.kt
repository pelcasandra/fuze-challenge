package com.fuze.di

import android.os.VibrationAttributes.Builder
import com.data.MatchRepositoryImpl
import com.data.MatchService
import com.domain.MatchRepository
import com.fuze.BuildConfig
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .client(client)
            .baseUrl(BuildConfig.WEB_API_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(tokenInterceptor: TokenInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(tokenInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): MatchService {
        return retrofit.create(MatchService::class.java)
    }

    @Singleton
    @Provides
    fun provideRemoteRepository(service: MatchService): MatchRepository = MatchRepositoryImpl(service)
}