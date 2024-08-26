package com.example.mob_ayoub_project.models

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob_ayoub_project.data.InfosFromOneRecipe
import kotlinx.coroutines.launch

class CreateRecipeViewModel : ViewModel() {

    /**
    * <p>This method is responsible for handling the addition of a recipe to the user's
    * list of favorites. It checks if the recipe is already marked as a favorite and,
    * if so, displays an appropriate message. If the recipe is not yet a favorite,
    * it adds the recipe to the favorites list
    */
    fun addFavoriteOrNot(context: Context, recipe: InfosFromOneRecipe) {
        viewModelScope.launch {
            Repository.addOrShowMessage(context, recipe)
        }
    }
}