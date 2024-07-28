package com.example.mob_ayoub_project.ui.screens.recipes

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.mob_ayoub_project.data.Cuisine
import com.example.mob_ayoub_project.data.Recipe
import com.example.mob_ayoub_project.models.CuisineViewModel
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

@Composable
fun AllRecipeFromCuisineScreen(
    cuisineChoosed : Cuisine,
    onRecipeChoosed: (Recipe) -> Unit = {},
) {
    //cet ecran va etre affiché une fois la cuisine choisie , il recherchera alors les recettes
    //et les affichera
    val cuisineViewModel: CuisineViewModel = viewModel()

    LaunchedEffect(cuisineChoosed) {
        cuisineViewModel.setCuisine(cuisineChoosed)
        cuisineViewModel.fetchRecipesFromCuisine()
    }


    LazyColumn() {
        items(items = cuisineViewModel.allRecipesFromCuisine.value) { recipe ->
            ColumnItem(
                modifier = Modifier,
                recipe = recipe,
                onClick = {
                    onRecipeChoosed(recipe)// une fois cliqué on passera l'id de la recette lors de
                    //la naviguation dans le Control.kt
                })
        }
    }
}

/**
 * Represents each recipe displayed from a specific cuisine
 */
@Composable
fun ColumnItem(modifier: Modifier, recipe: Recipe, onClick: () -> Unit) {
    Card(
        modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .aspectRatio(3f)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Box(
            modifier
                .padding(10.dp)
                .fillMaxSize(),

            )
        {
            Row {
                AsyncImage(
                    model = recipe.image,
                    contentDescription = "recipe picture",
                    modifier = Modifier
                        .size(90.dp)
                )
                Text(
                    text = recipe.title,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
        }
    }
}
