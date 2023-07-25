package com.example.drinkapp.data.paging_source

import android.annotation.SuppressLint
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.drinkapp.data.remote.DrinkApi
import com.example.drinkapp.domain.model.Drink
import javax.inject.Inject

class SearchDrinksContainingIngredientsSource @Inject constructor(
    private val drinkApi: DrinkApi,
    private val query: List<String>
): PagingSource<Int, Drink>(){
    private val separator ="-"

    private fun convertListToString(list: List<String>): String {
        val stringBuilder = StringBuilder()
        for (item in list){
            stringBuilder.append(item).append(separator)
        }
        stringBuilder.setLength(stringBuilder.length - separator.length)
        return stringBuilder.toString()
    }

    @SuppressLint("SuspiciousIndentation")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Drink> {
        return try {
            val apiResponses = drinkApi.searchDrinksByIngredients(ingredientFamilyName = convertListToString(list = query))
            val firstApiResponse = apiResponses.first()
/*
            if (!firstApiResponse.drinks.isNullOrEmpty()){
                LoadResult.Page(
                    data = apiResponses[1].drinks,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage
                )
            }else{
                */
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            //}

        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Drink>): Int? {
        return state.anchorPosition
    }
}