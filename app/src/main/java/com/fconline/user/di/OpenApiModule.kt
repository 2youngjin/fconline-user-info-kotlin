package com.fconline.user.di

import com.fconline.user.data.source.OpenApiService
import com.fconline.user.util.DateUtil
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OpenApiModule {
    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(
            GsonBuilder()
                .setDateFormat(DateUtil.serverDateFormat.toPattern())
                .create()
        )
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            connectTimeout(5, TimeUnit.SECONDS)
            readTimeout(5, TimeUnit.SECONDS)
            writeTimeout(5, TimeUnit.SECONDS)
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient.Builder,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://open.api.nexon.com")
            .addConverterFactory(gsonConverterFactory)
            .client(client.build())
            .build()
    }

    @Singleton
    @Provides
    fun providesOpenApiService(retrofit: Retrofit): OpenApiService =
        retrofit.create(OpenApiService::class.java)
}