package com.example.hiltworksample.repository

import com.example.hiltworksample.data.ApiService
import com.example.hiltworksample.data.model.UserResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SampleRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getUserData(): Response<UserResponse> {
        return apiService.getUsers()
    }
}