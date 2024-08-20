package com.example.mob_ayoub_project.models

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.mob_ayoub_project.R
import com.example.mob_ayoub_project.data.InfosFromOneRecipe
import com.example.mob_ayoub_project.data.Recipe
import com.example.mob_ayoub_project.database.FavoritesDatabase
import com.example.mob_ayoub_project.database.RecipeFavorite
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

object Repository {


    //mettre toutes les données ici car on récupérera tout, il y a des choses à rajouter


    private var database: FavoritesDatabase? = null
    private val _messageSnackBar = MutableStateFlow<String>("")
    val messageSnackBar: StateFlow<String> get() = _messageSnackBar


    fun initDatabase(context: Context) {
        if (database == null) {
            database = FavoritesDatabase.getInstance(context)
        }
    }


    fun updateMessageSnackBar(message  :String){
        _messageSnackBar.value = message
    }

    suspend fun insertFavoriteInDatabase(recipe: InfosFromOneRecipe) {
        database?.let { thedatabase ->

            val newRecipe = RecipeFavorite(
                0, recipe.image, recipe.title, recipe.veryHealthy,
                recipe.summary, recipe.instructions, recipe.extendedIngredients
            )

            thedatabase.recipeFavoritesDao().addNewRecipe(newRecipe)

        }
    }


    suspend fun getAllFavoritesRecipe(): List<RecipeFavorite> {
        database?.let { thedatabase ->

            return thedatabase.recipeFavoritesDao().getAllRecipe()
        }
        return listOf()
    }


    suspend fun removeFavoriteFromDatabase(recipe: RecipeFavorite) {
        database?.let { thedatabase ->
            thedatabase.recipeFavoritesDao().removeFavorite(recipe)

        }
    }

    //cette methode se trouve ici car je vais l utiliser dans l ecran details
    //on enregistre a partir d ici directement sans passé par favoriteViewModel
    //dans favorite viewmodel il y a juste pour supprimer les recettes
    suspend fun addOrShowMessage(recipe: InfosFromOneRecipe) {
        val allFavorites = getAllFavoritesRecipe()
        if (allFavorites.any { it.title == recipe.title }) {
            Log.i("Recette favorite", R.string.alreadyFavorites.toString())
            updateMessageSnackBar("la recette n'a pas été ajouté car présente")
        } else {
            insertFavoriteInDatabase(recipe)
            Log.i("Recette favorite", R.string.newFavorites.toString())
            updateMessageSnackBar("la recette ajouté ")
        }
    }

    suspend fun getFavoriteRecipeById(recipeId: Int): RecipeFavorite? {
        database?.let { thedatabase ->
            return thedatabase.recipeFavoritesDao().getRecipeById(recipeId)
        }
        return null
    }

    suspend fun showAlertForWifiOrNot(){
        updateMessageSnackBar("")

    }



}