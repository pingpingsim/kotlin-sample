package com.hanai.jiwa.di

import android.content.Context
import com.hanai.jiwa.data.DataRepository
import com.hanai.jiwa.data.DataRepositorySource
import com.hanai.jiwa.data.local.LocalData
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Tells Dagger this is a Dagger module
@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideDataRepository(dataRepository: DataRepository): DataRepositorySource
}
