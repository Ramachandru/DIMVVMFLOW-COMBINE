package com.example.dimvvmflow.network

import com.example.dimvvmflow.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ApiInterfaceImpl @Inject constructor(private val apiService: ApiService) : ApiInterface {
    override fun getUser(): Flow<List<User>> {
        return flow {
            emit(apiService.getUsers())
        }.flowOn(Dispatchers.IO)
            .catch { }
    }

    override fun getMoreUser(): Flow<List<User>> {
        return flow {
            emit(apiService.getMoreUser())
        }
    }
}