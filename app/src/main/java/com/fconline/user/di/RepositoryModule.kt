package com.fconline.user.di

import com.fconline.user.data.repository.DivisionRepositoryImpl
import com.fconline.user.data.repository.MatchIdRepositoryImpl
import com.fconline.user.data.repository.MatchTypeRepositoryImpl
import com.fconline.user.data.repository.MaxDivisionRepositoryImpl
import com.fconline.user.data.source.remote.ApiService
import com.fconline.user.data.repository.UserIdRepositoryImpl
import com.fconline.user.data.repository.UserInfoRepositoryImpl
import com.fconline.user.domain.repository.DivisionRepository
import com.fconline.user.domain.repository.MatchIdRepository
import com.fconline.user.domain.repository.MatchTypeRepository
import com.fconline.user.domain.repository.MaxDivisionRepository
import com.fconline.user.domain.repository.UserIdRepository
import com.fconline.user.domain.repository.UserInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun providesUserIdRepository(
        apiService: ApiService
    ): UserIdRepository = UserIdRepositoryImpl(apiService)

    @Provides
    @ViewModelScoped
    fun providesUserInfoRepository(
        apiService: ApiService
    ): UserInfoRepository = UserInfoRepositoryImpl(apiService)

    @Provides
    @ViewModelScoped
    fun providesMaxDivisionRepository(
        apiService: ApiService
    ): MaxDivisionRepository = MaxDivisionRepositoryImpl(apiService)

    @Provides
    @ViewModelScoped
    fun providesMatchTypeRepository(
        apiService: ApiService
    ): MatchTypeRepository = MatchTypeRepositoryImpl(apiService)

    @Provides
    @ViewModelScoped
    fun providesDivisionRepository(
        apiService: ApiService
    ): DivisionRepository = DivisionRepositoryImpl(apiService)

    @Provides
    @ViewModelScoped
    fun providesMatchId(
        apiService: ApiService
    ): MatchIdRepository = MatchIdRepositoryImpl(apiService)
}