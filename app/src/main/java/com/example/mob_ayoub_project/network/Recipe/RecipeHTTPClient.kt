package com.example.mob_ayoub_project.network.Recipe

import com.example.mob_ayoub_project.data.Cuisine
import com.example.mob_ayoub_project.data.DataPerson
import com.example.mob_ayoub_project.data.InfosFromOneRecipe
import com.example.mob_ayoub_project.data.Recipe
import com.example.mob_ayoub_project.data.RecipeSuggestion
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

    /*
    Search the recipes from cuisine
     */
    @GET("recipes/complexSearch/")
    suspend fun chooseCuisine(
        @Query("cuisine") cuisine: String,
        @Query("apiKey") apiKey : String): RecipesResponse

    /*
    Search informations of recipe from the id
     */
    @GET("recipes/{recipeId}/information")
    suspend fun infosForOneRecipe(
        @Path("recipeId") recipeId: Int,
        @Query("apiKey") apiKey : String): InfosFromOneRecipe



    /**
     * Retrieves a list of recipe suggestions based on a partial query string.
     *
     * <p>This method calls the "recipes/autocomplete" endpoint to get a list of recipe suggestions
     * that match the input query. The number of suggestions returned can be specified.
     * It is commonly used for implementing an autocomplete feature in recipe search functionalities.
     *
     */
    @GET("recipes/autocomplete")
    suspend fun autocompleteRecipes(
        @Query("query") query: String,
        @Query("number") number: Int,
        @Query("apiKey") apiKey: String
    ): List<RecipeSuggestion>



}