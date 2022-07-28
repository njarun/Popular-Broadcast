package com.popular.broadcast.di.module

import com.popular.broadcast.data.networking.repository.NewsRepositoryImpl
import com.popular.broadcast.domain.dto.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NewsModule {

    @Binds
    abstract fun bindNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository
}