package com.example.mob_ayoub_project.models

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob_ayoub_project.data.InfosFromOneRecipe
import com.example.mob_ayoub_project.data.Recipe
import com.example.mob_ayoub_project.data.Utils
import com.example.mob_ayoub_project.network.Recipe.RecipeService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeDetailsViewModel : ViewModel() {

    var recipeChoosedId: Int? = null
    var resultsInfosFromOneRecipe = mutableStateOf<InfosFromOneRecipe?>(null)



    fun setRecipeChoosedId(newRecipeId: Int) {
        recipeChoosedId = newRecipeId;
    }

    fun fetchInfosFromRecipe() {
        val currentRecipeId = recipeChoosedId
        Log.i("ID : ", currentRecipeId.toString())
        if (currentRecipeId != null) {
            viewModelScope.launch {
                try {
                    Log.i("ID : ", currentRecipeId.toString())

                    val responseForTheRecipe = RecipeService.recipeClient?.infosForOneRecipe(currentRecipeId, Utils.apiKeyRecipe)
                    responseForTheRecipe?.let { response ->
                        resultsInfosFromOneRecipe.value = response
                        Log.i("INFOS SUR RECETTE", resultsInfosFromOneRecipe.toString())
                    }
                } catch (e: Exception) {
                    Log.e("recherche recette infos", "erreur produite : ${e.message}", e)
                }
            }
        } else {
            Log.e("recherche recette infos", "Recette ID est nulle")
        }
    }

    fun addFavoriteOrNot(recipe: InfosFromOneRecipe){
        viewModelScope.launch {
            Log.i("Recette Favorite", "detailsViewModel")
            Repository.addOrShowMessage(recipe)
        }
    }
}