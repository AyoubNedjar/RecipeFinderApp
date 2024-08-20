package com.example.mob_ayoub_project.models

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob_ayoub_project.data.RecipeSuggestion
import com.example.mob_ayoub_project.data.Utils
import com.example.mob_ayoub_project.network.Recipe.RecipeService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShearchViewModel : ViewModel() {
    //ici les methodes qui appelerons ls requetes
    //exemple fetchResulsFromSheach

    private val _suggestions = MutableStateFlow<List<RecipeSuggestion>>(emptyList())
    val suggestions: StateFlow<List<RecipeSuggestion>> get() = _suggestions

    fun fetchRecipeSuggestions(query: String, number: Int) {
        viewModelScope.launch {
            try {
                RecipeService.initializeRecipeClient()
                val responseForTheRecipe = RecipeService.recipeClient?.autocompleteRecipes(
                    query = query,
                    number = number,
                    apiKey = Utils.apiKeyRecipe
                )

                responseForTheRecipe?.let {
                    _suggestions.value = it
                }
            } catch (e: Exception) {
            }
        }
    }
}