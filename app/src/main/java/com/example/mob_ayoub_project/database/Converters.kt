package com.example.mob_ayoub_project.database

import androidx.room.TypeConverter
import com.example.mob_ayoub_project.data.Cuisine
import com.example.mob_ayoub_project.data.Ingredients
import com.example.mob_ayoub_project.data.Recipe
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types


/**
 * we want to transform the list of ingredients into JSON format and reverse
 */
class Converters {

    private val moshi = Moshi.Builder().build()
    private val listType = Types.newParameterizedType(List::class.java, Ingredients::class.java)
    private val adapter = moshi.adapter<List<Ingredients>>(listType)
    private val cuisineAdapter = moshi.adapter(Cuisine::class.java)
    private val recipeAdapter = moshi.adapter(Recipe::class.java)


    @TypeConverter
    fun fromIngredientsList(ingredients: List<Ingredients>?): String? {
        return ingredients?.let { adapter.toJson(it) }
    }

    @TypeConverter
    fun toIngredientsList(ingredientsString: String?): List<Ingredients>? {
        return ingredientsString?.let { adapter.fromJson(it) }
    }

    @TypeConverter
    fun fromCuisine(cuisine: Cuisine): String {
        return cuisine.let { cuisineAdapter.toJson(it) }
    }

    @TypeConverter
    fun toCuisine(cuisineString: String): Cuisine? {
        return cuisineString.let { cuisineAdapter.fromJson(it) }
    }

    @TypeConverter
    fun fromRecipe(recipe: Recipe): String {
        return recipe.let { recipeAdapter.toJson(it) }
    }

    @TypeConverter
    fun toRecipe(recipeString: String): Recipe? {
        return recipeString.let { recipeAdapter.fromJson(it) }
    }



}
