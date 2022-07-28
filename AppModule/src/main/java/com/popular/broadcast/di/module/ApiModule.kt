package com.popular.broadcast.di.module

import com.popular.broadcast.data.networking.api.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)

}