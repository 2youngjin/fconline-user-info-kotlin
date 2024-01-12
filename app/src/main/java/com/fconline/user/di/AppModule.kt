package com.fconline.user.di

import com.fconline.user.data.remote.ApiService
import com.fconline.user.data.remote.UserIdRepositoryRemote
import com.fconline.user.domain.repository.UserIdRepository
import com.fconline.user.domain.usecase.GetUserIdUseCase
import com.fconline.user.presentation.viewmodel.AccountInfoViewModel
import com.fconline.user.util.HeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): HeaderInterceptor {
        return HeaderInterceptor("live_901d5ca7706fde3f983d01b7990cad822aeadc51ffdea73da5b295535c277d57cefff0e1831c601b1b53fc439bbdf214")
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =
            HttpLoggingInterceptor.Level.BODY // You can use Level.BASIC or Level.HEADERS for less verbose logging
        return interceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        headerInterceptor: HeaderInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://open.api.nexon.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepositoryRemote(apiService: ApiService): UserIdRepositoryRemote {
        return UserIdRepositoryRemote(apiService)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userRepositoryRemote: UserIdRepositoryRemote): UserIdRepository {
        return UserIdRepository(userRepositoryRemote)
    }

    @Provides
    @Singleton
    fun provideGetUserIdUseCase(userRepository: UserIdRepository): GetUserIdUseCase {
        return GetUserIdUseCase(userRepository)
    }

    @Provides
    fun provideUserViewModel(getUserIdUseCase: GetUserIdUseCase): AccountInfoViewModel {
        return AccountInfoViewModel(getUserIdUseCase)
    }

}