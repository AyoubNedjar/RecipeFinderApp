package com.example.mob_ayoub_project.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mob_ayoub_project.data.InfosFromOneRecipe


// Represents the operations to perform on the database
@Dao
interface RecipeFavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewRecipe(recipe : RecipeFavorite)


    @Query("SELECT * from RecipeFavorite")
    suspend fun getAllRecipe(): List<RecipeFavorite>


    /**
     * Retrieves a favorite recipe from the database based on its unique ID.
     *
     * <p>This method executes a SQL query to fetch a recipe from the `RecipeFavorite` table
     * that matches the specified ID. If a recipe with the given ID is found, it is returned;
     * otherwise, `null` is returned. This can be used to check if a recipe is marked as a favorite
     * by the user.
     */
    @Query("SELECT * from RecipeFavorite WHERE id = :recipeId")
    suspend fun getRecipeById(recipeId: Int): RecipeFavorite?


    @Delete
    suspend fun removeFavorite(recipe : RecipeFavorite)
}