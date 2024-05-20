package com.example.mob_ayoub_project.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [RecipeFavorite::class], version = 1, exportSchema = false)

@TypeConverters(Converters::class)
abstract class FavoritesDatabase () : RoomDatabase() {


    /*
    Determines which DAO to use.
    */
    abstract fun recipeFavoritesDao():RecipeFavoriteDao


    /*
    Defines the initialization code of the database,
    The static variable `sInstance` will hold a reference to the unique instance of `NoteDatabase`.
    The `getInstance` function checks if an instance already exists.
     If it doesn't, it will create a new one.
     (Singleton Pattern)
    */
    companion object {
        // The context represents the state of an activity,
        // so we can pass it as a parameter to a function.
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