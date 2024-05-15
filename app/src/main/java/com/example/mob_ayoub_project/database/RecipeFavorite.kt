package com.example.mob_ayoub_project.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mob_ayoub_project.data.Ingredients


@Entity(tableName = "RecipeFavorite")
data class RecipeFavorite(

    @PrimaryKey(autoGenerate = true)
    val id:Int,

    @ColumnInfo(name = "image" )
    val image : String? = null,

    @ColumnInfo(name = "title" )
    val title : String? = null,

    @ColumnInfo(name = "veryHealthy" )
    val veryHealthy : Boolean? = null,

    @ColumnInfo(name = "summary" )
    val summary :String? = null,

    @ColumnInfo(name = "instructions" )
    val instructions : String? = null,

    @ColumnInfo(name = "extendedIngredients" )
    val extendedIngredients : List<Ingredients>
)
