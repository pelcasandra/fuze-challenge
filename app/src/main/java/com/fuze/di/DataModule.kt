package com.fuze.di

import com.data.match.MatchRepositoryImpl
import com.data.match.MatchService
import com.data.team.TeamRepositoryImpl
import com.data.team.TeamService
import com.domain.match.MatchRepository
import com.domain.team.TeamRepository
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
    fun provideMatchService(retrofit: Retrofit): MatchService {
        return retrofit.create(MatchService::class.java)
    }

    @Singleton
    @Provides
    fun provideMatchRepository(service: MatchService): MatchRepository = MatchRepositoryImpl(service)

    @Provides
    @Singleton
    fun provideTeamService(retrofit: Retrofit): TeamService {
        return retrofit.create(TeamService::class.java)
    }

    @Singleton
    @Provides
    fun provideTeamRepository(service: TeamService): TeamRepository = TeamRepositoryImpl(service)
}