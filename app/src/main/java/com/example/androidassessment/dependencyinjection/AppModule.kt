package com.example.androidassessment.dependencyinjection

import com.example.androidassessment.model.AppRepository
import com.example.androidassessment.networkservice.NetworkDataSource
import com.example.androidassessment.networkservice.RetrofitService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideOKHttpClient(): OkHttpClient {
        return  OkHttpClient.Builder()
            .readTimeout(1200, TimeUnit.SECONDS)
            .connectTimeout(1200,TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideGSON(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl("https://dl.dropboxusercontent.com")
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideAppRepository(): AppRepository = AppRepository

    @Provides
    @Singleton
    fun provideRetrofitService(retrofit: Retrofit) : RetrofitService = retrofit.create(RetrofitService::class.java)

    @Provides
    @Singleton
    fun provideNetworkDataSource(retrofitService: RetrofitService): NetworkDataSource = NetworkDataSource.getInstance(retrofitService)

}