package com.hansheung.mob_project.core

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
    @Named("msg1")
    fun provideGreetingMsg1(): String{
        return "Hello Dagger Hilt 2 1"
    }

}