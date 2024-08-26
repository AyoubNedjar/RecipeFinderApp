package com.example.mob_ayoub_project.ui.screens.recipes


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.mob_ayoub_project.data.Utils.HtmlText
import com.example.mob_ayoub_project.models.FavoriteOneRecipeDetailsViewModel


@Composable
fun OneRecipeFromFavorite(
    recipeIdDb : Int
) {
    Log.i("Id db from recipe : ", recipeIdDb.toString())

    val viewModel: FavoriteOneRecipeDetailsViewModel = viewModel()

    val recipe by viewModel.recipe.collectAsState()

    LaunchedEffect(recipeIdDb) {
        viewModel.fetchRecipeById(recipeIdDb)
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = recipe?.image),
            contentDescription = "Recipe Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )


        recipe?.title?.let {
            Text(
                text = it,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

        Text(
            text = "Santé",
            style = TextStyle(
                textDecoration = TextDecoration.Underline,
                fontSize = 20.sp
            )
        )
        recipe?.veryHealthy.let { isHealthy ->
            val healthIndicator = if (isHealthy == true) "Healthy" else "Not very healthy"
            Text(
                text = healthIndicator,
                fontSize = 16.sp,
                color = if (isHealthy == true) Color.Green else Color.Red,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Text(
            text = "Résumé",
            style = TextStyle(
                textDecoration = TextDecoration.Underline,
                fontSize = 20.sp
            )
        )
        recipe?.summary?.let { summary ->
            HtmlText(html = summary.trimIndent())
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Ingredients",
            style = TextStyle(
                textDecoration = TextDecoration.Underline,
                fontSize = 20.sp
            )
        )
        recipe?.extendedIngredients?.forEach { ingredient ->
            Text(
                text = "- ${ingredient.name}",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Instructions",
            style = TextStyle(
                textDecoration = TextDecoration.Underline,
                fontSize = 20.sp
            )
        )
        recipe?.instructions?.let { instructions ->
            HtmlText(html = instructions.trimIndent())
        }
        Spacer(modifier = Modifier.height(30.dp))

    }
}

