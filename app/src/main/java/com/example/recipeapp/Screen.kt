package com.example.recipeapp

import okhttp3.Route

sealed class Screen(val route: String) {
    object RecipeScreen:Screen("recipeScreen")
    object DetailsScreen:Screen("detailsScreen")
}