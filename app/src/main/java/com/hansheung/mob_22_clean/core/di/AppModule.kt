package com.hansheung.mob_22_clean.core.di

import com.hansheung.mob_22_clean.data.repo.TasksRepoImpl
import com.hansheung.mob_22_clean.domain.repo.TasksRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    @Named("msg0")
    fun provideGreetingMsg(): String{
        return "Hello Dagger Hilt 2"
    }

    @Provides
    @Singleton
    fun provideTaskRepo(): TasksRepo{
        return TasksRepoImpl()
    }

}