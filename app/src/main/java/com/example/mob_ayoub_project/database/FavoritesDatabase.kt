package com.example.mob_ayoub_project.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RecipeFavorite::class], version = 1, exportSchema = false)
abstract class FavoritesDatabase () : RoomDatabase() {
    abstract fun recipeFavoritesDao():RecipeFavoriteDao
}