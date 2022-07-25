package com.popular.broadcast.di.module

import com.popular.broadcast.presentation.home.adapter.NewsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object HomeModule {

    @Provides
    fun provideNewsAdapter(): NewsAdapter {
        return NewsAdapter()
    }
}