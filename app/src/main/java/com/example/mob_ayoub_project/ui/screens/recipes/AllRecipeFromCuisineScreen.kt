package com.example.mob_ayoub_project.ui.screens.recipes

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mob_ayoub_project.data.Cuisine
import com.example.mob_ayoub_project.data.Recipe
import com.example.mob_ayoub_project.network.Recipe.RecipesResponse


@Composable
fun AllRecipeFromCuisineScreen(
    modifier: Modifier = Modifier,
    allRecipe: List<Recipe>,
    onRecipeChoosed: (Recipe) -> Unit = {},
    ) {

    Log.i("Liste attendu pour affcihage", allRecipe.toString())
    LazyColumn() {
        items(items = allRecipe) {recipe ->
            ColumnItem(
                modifier = Modifier,
                recipe = recipe,
                onClick = {
                    Log.i("Recete clickée", recipe.toString())
                    onRecipeChoosed(recipe)})
        }
    }

}


@Composable
fun ColumnItem(modifier: Modifier, recipe: Recipe, onClick  :() -> Unit) {
    Card(
        modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .aspectRatio(3f)
            .clickable (onClick = onClick),// cette action va etre attribué a l action de clicker
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Box(
            modifier
                .padding(10.dp)
                .fillMaxSize(),
//            contentAlignment = Alignment.Center
        )
        {
            AsyncImage(
                model = recipe.image,
                contentDescription = "recipe picture",
                modifier = Modifier
                    .size(90.dp)
            )
            Column() {
                Text(text = recipe.title, fontSize = 28.sp, fontWeight = FontWeight.Bold)

            }

        }
    }
}


/*fun Pre(){
    AllRecipeFromCuisineScreen(allRecipe = )
}*/