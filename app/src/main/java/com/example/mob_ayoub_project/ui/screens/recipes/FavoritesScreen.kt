package com.example.mob_ayoub_project.ui.screens.recipes

import android.util.Log
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mob_ayoub_project.data.Recipe
import com.example.mob_ayoub_project.database.RecipeFavorite


@Composable
fun DisplayFavoritesRecipe(favoritesList : List<RecipeFavorite>,
                           contentPadding: PaddingValues,
                           onSelectionDeleted : (RecipeFavorite) -> Unit,
                           onRecipeClickable: (RecipeFavorite) -> Unit,
                           modifier : Modifier,){

    if (favoritesList.size==0){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Vous n'avez aucun favoris ",
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 24.sp)
            )
        }
    }else{
        LazyColumn(modifier = modifier, contentPadding = contentPadding) {
            items(items = favoritesList) {theRecipeFavorite->
                ColumnItem2(modifier, theRecipeFavorite, onSelectionDeleted, onRecipeClickable)
            }
        }
    }
}


//TODO rajouter une option pour supprimer avec une image de poubelle

@Composable
fun ColumnItem2(modifier: Modifier = Modifier, recipe: RecipeFavorite, onSelectionDeleted: (RecipeFavorite) -> Unit,onRecipeClickable: (RecipeFavorite) -> Unit ){

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
//            contentAlignment = Alignment.Center
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
                            onSelectionDeleted(recipe)
                        }

                )
            }

        }
    }
}