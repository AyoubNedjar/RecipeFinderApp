package com.example.mob_ayoub_project.database

import androidx.room.TypeConverter
import com.example.mob_ayoub_project.data.Ingredients
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types


/**
 * we want to transform the list of ingredients into JSON format and reverse
 */
class Converters {

    private val moshi = Moshi.Builder().build()
    private val listType = Types.newParameterizedType(List::class.java, Ingredients::class.java)
    private val adapter = moshi.adapter<List<Ingredients>>(listType)

    @TypeConverter
    fun fromIngredientsList(ingredients: List<Ingredients>?): String? {
        return ingredients?.let { adapter.toJson(it) }
    }

    @TypeConverter
    fun toIngredientsList(ingredientsString: String?): List<Ingredients>? {
        return ingredientsString?.let { adapter.fromJson(it) }
    }



}
