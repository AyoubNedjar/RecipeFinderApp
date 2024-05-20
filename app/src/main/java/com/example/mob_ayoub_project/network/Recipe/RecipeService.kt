package com.example.mob_ayoub_project.network.Recipe

import android.util.Log
import com.example.mob_ayoub_project.data.Utils
import com.example.mob_ayoub_project.network.login.AuthHTTPCient
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json

object RecipeService {
   var recipeClient: RecipeHTTPClient? = null

    fun initializeRecipeClient() {
        try {

            val jsonConverter = MoshiConverterFactory.create()
            val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
                .addConverterFactory(jsonConverter).baseUrl(Utils.baseURLRecipe)

            val retrofit: Retrofit = retrofitBuilder.build()
            recipeClient = retrofit.create(RecipeHTTPClient::class.java)
        } catch (e: Exception) {
            Log.e("Recipe from cuisine", e.message, e)
            e.printStackTrace()
        }
    }

}