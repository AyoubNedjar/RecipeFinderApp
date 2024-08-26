package com.example.mob_ayoub_project.models

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob_ayoub_project.R
import com.example.mob_ayoub_project.data.InfosFromOneRecipe
import com.example.mob_ayoub_project.database.RecipeFavorite
import kotlinx.coroutines.launch

class FavoriteViewModel : ViewModel() {

    var favoritesList: MutableState<List<RecipeFavorite>> = mutableStateOf(listOf())

    init {
        viewModelScope.launch {
            favoritesList.value = Repository.getAllFavoritesRecipe()
        }
    }

    fun deleteFavoriteFromTheDatabase(recipe: RecipeFavorite) {
        viewModelScope.launch {
            Repository.removeFavoriteFromDatabase(recipe)
            // Here we don't need to create new methods to display and retrieve data
            // because it's assumed that they will be done directly once we have deleted a new note
            favoritesList.value = Repository.getAllFavoritesRecipe()
        }
    }
}