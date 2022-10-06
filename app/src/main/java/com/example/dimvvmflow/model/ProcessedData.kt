package com.example.dimvvmflow.model

sealed class ProcessedData {
    data class Success(val listUser: List<User>) : ProcessedData()

    data class Error(val errorMsg: String) : ProcessedData()

    object Loading : ProcessedData()
}
