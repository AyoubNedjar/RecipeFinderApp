package com.example.mob_ayoub_project.ui.screens.recipes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.mob_ayoub_project.data.InfosFromOneRecipe
import com.example.mob_ayoub_project.data.Recipe


@Composable
fun DisplayRecipeChoosed(
    recipe : InfosFromOneRecipe
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Affichage de l'image de la recette
        recipe.image?.let { imageUrl ->
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = "Recipe Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }

        // Affichage du titre de la recette
        recipe.title?.let { title ->
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

        // Affichage de l'indicateur de santé
        recipe.veryHealthy?.let { isHealthy ->
            val healthIndicator = if (isHealthy) "Sain" else "Pas très sain"
            Text(
                text = healthIndicator,
                fontSize = 16.sp,
                color = if (isHealthy) Color.Green else Color.Red,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        // Affichage du résumé de la recette
        recipe.summary?.let { summary ->
            Text(
                text = summary,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // Affichage des ingrédients
        recipe.extendedIngredients.forEach { ingredient ->
            Text(
                text = "- ${ingredient.name}",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }

        // Affichage des instructions
        recipe.instructions?.let { instructions ->
            Text(
                text = instructions,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

    }


}