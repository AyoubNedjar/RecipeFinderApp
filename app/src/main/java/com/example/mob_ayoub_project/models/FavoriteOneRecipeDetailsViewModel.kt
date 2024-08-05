package com.example.mob_ayoub_project.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob_ayoub_project.data.InfosFromOneRecipe
import com.example.mob_ayoub_project.database.RecipeFavorite
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteOneRecipeDetailsViewModel : ViewModel() {

    private val _recipe = MutableStateFlow<RecipeFavorite?>(null)
    val recipe: StateFlow<RecipeFavorite?> = _recipe
    fun fetchRecipeById(recipeId: Int) {
        viewModelScope.launch {
            val recipe = Repository.getFavoriteRecipeById(recipeId)
            _recipe.value = recipe
        }
    }
}