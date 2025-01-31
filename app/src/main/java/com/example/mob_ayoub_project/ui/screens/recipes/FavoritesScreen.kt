package com.example.mob_ayoub_project.ui.screens.recipes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.mob_ayoub_project.database.RecipeFavorite
import com.example.mob_ayoub_project.models.FavoriteViewModel
import com.example.mob_ayoub_project.models.RecipeDetailsViewModel


@Composable
fun DisplayFavoritesRecipe(
    contentPadding: PaddingValues,
    onRecipeClickable: (RecipeFavorite) -> Unit,
    modifier: Modifier,
) {

    val favoritesViewModel: FavoriteViewModel = viewModel()

    if (favoritesViewModel.favoritesList.value.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Vous n'avez aucun favoris ",
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 24.sp)
            )
        }
    } else {
        LazyColumn(modifier = modifier, contentPadding = contentPadding) {
            items(items = favoritesViewModel.favoritesList.value) { theRecipeFavorite ->
                ColumnItem2(modifier,favoritesViewModel, theRecipeFavorite,onRecipeClickable)
            }
        }
    }
}


@Composable
fun ColumnItem2(
    modifier: Modifier = Modifier,
    viewModel : FavoriteViewModel,
    recipe: RecipeFavorite,
    onRecipeClickable: (RecipeFavorite) -> Unit
) {

    Card(
        modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .aspectRatio(3f)
            .clickable {
                onRecipeClickable(recipe)
            },
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
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                recipe.image?.let {
                    AsyncImage(
                        model = recipe.image,
                        contentDescription = "favorite picture",
                        modifier = Modifier
                            .size(90.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                recipe.title?.let {
                    Text(
                        text = it,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                }

                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "suppression des favoris",
                    tint = Color.Red,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            viewModel.deleteFavoriteFromTheDatabase(recipe)
                        }

                )
            }

        }
    }
}