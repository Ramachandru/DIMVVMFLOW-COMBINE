package com.example.dimvvmflow.network

import com.example.dimvvmflow.model.User
import kotlinx.coroutines.flow.Flow

interface ApiInterface {
    fun getUser() : Flow<List<User>>

    fun getMoreUser() : Flow<List<User>>
}