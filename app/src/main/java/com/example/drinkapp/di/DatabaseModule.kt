package com.example.drinkapp.di

import android.content.Context
import androidx.room.Room
import com.example.drinkapp.data.local.DrinkDatabase
import com.example.drinkapp.util.Constants.DRINK_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        DrinkDatabase::class.java,
        DRINK_DATABASE
    ).build()

}