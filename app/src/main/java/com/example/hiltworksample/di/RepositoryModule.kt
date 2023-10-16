package com.example.hiltworksample.di

import com.example.hiltworksample.data.ApiService
import com.example.hiltworksample.repository.SampleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(apiService: ApiService): SampleRepository {
        return SampleRepository(apiService)
    }
}