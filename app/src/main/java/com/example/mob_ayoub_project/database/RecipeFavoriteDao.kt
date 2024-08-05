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

    @Query("SELECT * from RecipeFavorite WHERE id = :recipeId")
    suspend fun getRecipeById(recipeId: Int): RecipeFavorite?

    @Delete
    suspend fun removeFavorite(recipe : RecipeFavorite)
}