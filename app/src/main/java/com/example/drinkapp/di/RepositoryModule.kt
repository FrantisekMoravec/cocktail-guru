package com.example.drinkapp.di

import com.example.drinkapp.data.repository.Repository
import com.example.drinkapp.domain.use_cases.UseCases
import com.example.drinkapp.domain.use_cases.get_all_drinks.GetAllDrinksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases{
        return UseCases(
            getAllDrinksUseCase = GetAllDrinksUseCase(repository)
        )
    }

}