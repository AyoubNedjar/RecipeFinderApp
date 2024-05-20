package com.example.mob_ayoub_project.models

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob_ayoub_project.data.Cuisine
import com.example.mob_ayoub_project.data.InfosFromOneRecipe
import com.example.mob_ayoub_project.data.Recipe
import com.example.mob_ayoub_project.data.Utils
import com.example.mob_ayoub_project.database.RecipeFavorite
import com.example.mob_ayoub_project.network.Recipe.RecipeService
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel (){


    private var cuisineChoosed = mutableStateOf<Cuisine?>(null)


    var results: MutableState<List<Recipe>> = mutableStateOf(emptyList())

    var recipeChoosed  = mutableStateOf<Recipe?>(null)

    var resultsInfosFromOneRecipe = mutableStateOf<InfosFromOneRecipe?>(null)

    var favoritesList : MutableState<List<RecipeFavorite>> = mutableStateOf(listOf())




    init {
        viewModelScope.launch {
            favoritesList.value = Repository.getAllFavoritesRecipe()
        }
    }

    fun addFavoriteInTheDatabase(recipe : InfosFromOneRecipe){
        viewModelScope.launch {
            Repository.insertFavoriteInDatabase(recipe)
            // Here we don't need to create new methods to display and retrieve data because
            // it's assumed that they will be done directly once we have inserted a new note.
            favoritesList.value = Repository.getAllFavoritesRecipe()
        }
    }
    fun deleteFavoriteFromTheDatabase(recipe : RecipeFavorite){
        viewModelScope.launch {
            Repository.removeFavoriteFromDatabase(recipe)
            // Here we don't need to create new methods to display and retrieve data
            // because it's assumed that they will be done directly once we have deleted a new note
            favoritesList.value = Repository.getAllFavoritesRecipe()
        }
    }

    fun setRecipeChoosed(newRecipe : Recipe){
        recipeChoosed.value = newRecipe
    }
    fun setCuisine(newCuisine : Cuisine) {
        cuisineChoosed.value = newCuisine;

    }
    fun fetchRecipesFromCuisine(){
        viewModelScope.launch {
            try {
                val cuisine = cuisineChoosed.value
                if(cuisine!=null){
                    RecipeService.initializeRecipeClient()
                    val responseRecipes = RecipeService.recipeClient?.chooseCuisine(cuisine.name, Utils.apiKeyRecipe)
                    if (responseRecipes != null) {
                        results.value= responseRecipes.results
                        Log.i("Recipe from Cuisine", results.toString())
                    }else {
                        Log.e("Recipe from Cuisine", "Reponse de la recectte est null")
                    }
                }else{
                    Log.e("Recipe from Cuisine", "Aucune cuisine sélectionnée")
                }
            }catch(e: Exception){
                Log.e("Recipe from Cuisine", "La reception des recette n'a pas fonctionné")
                Log.e("Recipe from Cuisine", e.message, e)
                e.printStackTrace()
            }
        }
    }


    fun fetchInfosFromRecipe(){

        val idRecipeChoosed = recipeChoosed.value?.id
        if (idRecipeChoosed != null) {
            viewModelScope.launch {
                try {
                    val responseForTheRecipe = RecipeService.recipeClient?.infosForOneRecipe(idRecipeChoosed, Utils.apiKeyRecipe)

                    responseForTheRecipe?.let { response ->
                        resultsInfosFromOneRecipe.value = response
                        Log.i("INFOS SUR RECETTE", resultsInfosFromOneRecipe.toString())
                    }

                } catch (e: Exception) {
                    // Gérer l'erreur ici
                    Log.e("recherche recette infos ", "erreur produite : ${e.message}", e)
                }
            }
        } else {
            Log.e("recherche recette infos", "Recette ID est nulle")
        }
    }
}