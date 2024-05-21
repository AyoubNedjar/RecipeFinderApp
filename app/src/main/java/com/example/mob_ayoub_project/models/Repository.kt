package com.example.mob_ayoub_project.models

import android.content.Context
import com.example.mob_ayoub_project.data.InfosFromOneRecipe
import com.example.mob_ayoub_project.database.FavoritesDatabase
import com.example.mob_ayoub_project.database.RecipeFavorite

object Repository {

    private var database: FavoritesDatabase? = null


    fun initDatabase(context: Context) {
        if (database == null) {
            database = FavoritesDatabase.getInstance(context)
        }
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