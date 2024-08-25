package com.example.mob_ayoub_project.models

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob_ayoub_project.data.InfosFromOneRecipe
import kotlinx.coroutines.launch

class CreateRecipeViewModel : ViewModel() {

    fun addFavoriteOrNot(context: Context, recipe: InfosFromOneRecipe) {
        viewModelScope.launch {
            Repository.addOrShowMessage(context, recipe)
        }
    }
}