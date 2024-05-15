package com.example.mob_ayoub_project.network.Recipe

import com.example.mob_ayoub_project.data.Cuisine
import com.example.mob_ayoub_project.data.DataPerson
import com.example.mob_ayoub_project.data.InfosFromOneRecipe
import com.example.mob_ayoub_project.data.Recipe
import com.example.mob_ayoub_project.network.login.TokenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeHTTPClient {

    @Headers(
        "Content-Type: application/json",
    )
    @GET("recipes/complexSearch/")
    suspend fun chooseCuisine(
        @Query("cuisine") cuisine: String,
        @Query("apiKey") apiKey : String): RecipesResponse

    @GET("recipes/{recipeId}/information")
    suspend fun infosForOneRecipe(
        @Path("recipeId") recipeId: Int,
        @Query("apiKey") apiKey : String): InfosFromOneRecipe


}