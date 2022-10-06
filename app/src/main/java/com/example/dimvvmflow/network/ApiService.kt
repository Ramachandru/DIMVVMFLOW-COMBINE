package com.example.dimvvmflow.network

import com.example.dimvvmflow.model.User
import retrofit2.http.GET

interface ApiService {
    companion object {
        const val BASE_URL: String = "https://5e510330f2c0d300147c034c.mockapi.io/";
    }

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("more-users")
    suspend fun getMoreUser(): List<User>
}