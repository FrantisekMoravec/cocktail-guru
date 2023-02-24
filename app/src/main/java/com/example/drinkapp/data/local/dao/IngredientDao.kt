package com.example.drinkapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.drinkapp.domain.model.Ingredient

@Dao
interface IngredientDao {

    @Query("SELECT * FROM ingredient_table ORDER BY id ASC")
    fun getAllIngredients(): PagingSource<Int, Ingredient>

    @Query("SELECT * FROM ingredient_table WHERE id=:ingredientId")
    fun getSelectedIngredient(ingredientId: Int): Ingredient

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addIngredients(ingredients: List<Ingredient>)

    @Query("DELETE FROM ingredient_table")
    suspend fun deleteAllIngredients()

}