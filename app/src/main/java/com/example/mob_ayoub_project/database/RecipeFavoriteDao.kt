package com.example.mob_ayoub_project.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mob_ayoub_project.data.InfosFromOneRecipe


//représente les opérations à effectuer sur la db
@Dao
interface RecipeFavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewRecipe(recipe : RecipeFavorite)


    @Query("SELECT * from RecipeFavorite")
    suspend fun getAllRecipe(): List<RecipeFavorite>

    @Delete
    suspend fun deleteRecipeFromFavorites(recipe : RecipeFavorite)
}