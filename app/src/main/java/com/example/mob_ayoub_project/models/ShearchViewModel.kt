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

    private val _suggestions = MutableStateFlow<List<RecipeSuggestion>>(emptyList())
    val suggestions: StateFlow<List<RecipeSuggestion>> get() = _suggestions

    /**
     * Fetches recipe suggestions based on a query string.
     *
     * <p>This method is responsible for retrieving a list of recipe suggestions from the API
     * based on the provided query string. It uses the `RecipeService` to initialize the client
     * and make a network request to the `autocompleteRecipes` endpoint. The method runs within
     * the `viewModelScope` coroutine, ensuring that the network request is performed asynchronously.
     *
     * <p>If the request is successful, the resulting list of recipe suggestions is stored in the
     * `_suggestions` LiveData, making it available to the UI. If an exception occurs during the
     * process, it is caught, but no specific error handling is implemented.
     *
     * @param query  The partial query string to search for matching recipes.
     * @param number The maximum number of recipe suggestions to retrieve.
     */
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
                // Exception caught, but no action is taken.
            }
        }
    }

}