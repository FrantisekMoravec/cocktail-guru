package com.example.drinkapp.presentation.screens.search_drinks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/** viewmodel obrazovky pro vyhledávání drinků - zajišťuje že data zůstanou načtená i při změně stavu */
@HiltViewModel
class DrinksSearchViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchedDrinks = MutableStateFlow<PagingData<Drink>>(PagingData.empty())
    val searchedDrinks = _searchedDrinks

    //v základu je id 1 - naviguje na první drink
    var drinkId by mutableStateOf(1)

    fun drinksUpdateSearchQuery(query: String){
        _searchQuery.value = query
    }

    fun searchDrinks(query: String){
        viewModelScope.launch(Dispatchers.IO){
            useCases.searchDrinksUseCase(query = query).cachedIn(viewModelScope).collect {
                _searchedDrinks.value = it
            }
        }
    }
}