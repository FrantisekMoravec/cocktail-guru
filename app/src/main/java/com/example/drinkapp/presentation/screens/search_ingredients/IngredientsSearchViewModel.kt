package com.example.drinkapp.presentation.screens.search_ingredients

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.drinkapp.domain.model.IngredientFamily
import com.example.drinkapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IngredientsSearchViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchedIngredientFamilies = MutableStateFlow<PagingData<IngredientFamily>>(PagingData.empty())
    val searchedIngredientFamilies = _searchedIngredientFamilies

    fun ingredientFamiliesUpdateSearchQuery(query: String){
        _searchQuery.value = query
    }

    fun searchIngredientFamilies(query: String){
        viewModelScope.launch(Dispatchers.IO){
            useCases.searchIngredientFamiliesUseCase(query = query).cachedIn(viewModelScope).collect{
                _searchedIngredientFamilies.value = it
            }
        }
    }

}