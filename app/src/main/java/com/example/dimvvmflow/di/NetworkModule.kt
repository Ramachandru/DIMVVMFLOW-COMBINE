package com.example.dimvvmflow.di

import com.example.dimvvmflow.network.ApiInterface
import com.example.dimvvmflow.network.ApiInterfaceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Singleton
    @Binds
    abstract fun bindApiInterface(
        apiInterfaceImpl: ApiInterfaceImpl
    ): ApiInterface
}