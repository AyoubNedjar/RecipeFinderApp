package com.example.mob_ayoub_project.models

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
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

    //pour la mettre à jour à partir de l'ecran DetailsRecipe pour eviter de créer une instance
    //de FavoritesViewModel dans DetailsRecipe et d'avoir un seul viewModel par ecran
    var currentFavoriteRecipe = mutableStateOf<InfosFromOneRecipe?>(null)


    private val _messageSnackBar = MutableStateFlow<String>("")
    val messageSnackBar: StateFlow<String> get() = _messageSnackBar


    //si pas de solution pour l ajout, ajouté la rectte dans le repository , ajouter une methode
    //qui verifie si elle se trouve déja cela sans appeler le favoritemodelview et sans utiliser
    //la variable de la liste du modelview

    fun initDatabase(context: Context) {
        if (database == null) {
            database = FavoritesDatabase.getInstance(context)
        }
    }


    fun updateMessageSnackBar(message  :String){
        Log.i("Favorite recipe", "message snackbar")
        _messageSnackBar.value = message
    }
    fun updateCurrentFavoriteRecipe(recipe: InfosFromOneRecipe?) {
        Log.i("Recette favorite", "UPDATE RECIPE IN REPOSITORY : ")
        currentFavoriteRecipe.value = recipe
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
}