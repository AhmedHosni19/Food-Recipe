package com.example.recipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Error

class MainViewModel:ViewModel() {

    private val _categoryState= mutableStateOf(RecipeState())
    val categoryState: State<RecipeState> =_categoryState

    init {
        fetchCategories()
    }

    private fun fetchCategories(){
        viewModelScope.launch {
            try {
                val response= recipeService?.getCategories()
                _categoryState.value= response?.categories?.let {
                    _categoryState.value.copy(
                        list = it,
                        loading = false,
                        error = null
                    )
                }!!

            }catch (e: Exception){
                _categoryState.value=_categoryState.value.copy(
                    loading = false,
                    error = "Error fetching Categories ${e.message}",

                )
            }
        }
    }

    data class RecipeState(
        val loading:Boolean=true,
        val list:List<Category> = emptyList(),
        val error: String?=null
    )
}