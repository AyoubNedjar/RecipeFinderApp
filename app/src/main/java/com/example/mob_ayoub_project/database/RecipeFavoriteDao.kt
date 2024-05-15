package com.example.mob_ayoub_project.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.mob_ayoub_project.data.InfosFromOneRecipe

@Dao
interface RecipeFavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewRecipe(recipe : RecipeFavorite)

    @Delete
    suspend fun deleteRecipeFromFavorites(recipe : RecipeFavorite)
}