package com.example.drinkapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.drinkapp.data.local.dao.DrinkDao
import com.example.drinkapp.data.local.dao.DrinkRemoteKeysDao
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.DrinkRemoteKeys

/** databáze uchovávající stažená data */
@Database(
    entities = [
        /*
        Ingredient::class,
        IngredientRemoteKeys::class,
        */
        Drink::class,
        DrinkRemoteKeys::class
               ],
    version = 1
)
@TypeConverters(DatabaseConverter::class)
abstract class DrinkDatabase: RoomDatabase() {

    abstract fun drinkDao(): DrinkDao
    abstract fun drinkRemoteKeysDao(): DrinkRemoteKeysDao
    /*
    abstract fun ingredientDao(): IngredientDao
    abstract fun ingredientRemoteKeysDao(): IngredientRemoteKeysDao
    */
}