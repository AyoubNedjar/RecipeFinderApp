package com.example.mob_ayoub_project.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [RecipeFavorite::class], version = 1, exportSchema = false)

@TypeConverters(Converters::class)
abstract class FavoritesDatabase () : RoomDatabase() {

    abstract fun recipeFavoritesDao():RecipeFavoriteDao

    companion object {
        //le context est l état d une activité donc on peut lui passé en param un contexte
        private const val DATABASE_NAME = "recipes_db"
        private var sInstance: FavoritesDatabase? = null
        fun getInstance(context: Context): FavoritesDatabase {
            if (sInstance == null) {
                val dbBuilder = Room.databaseBuilder(
                    context.applicationContext,
                    FavoritesDatabase::class.java,
                    DATABASE_NAME
                )
                sInstance = dbBuilder.build()
            }
            return sInstance!!
        }
    }
}