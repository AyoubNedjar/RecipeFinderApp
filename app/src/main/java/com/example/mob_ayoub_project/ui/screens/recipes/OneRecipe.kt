package com.example.mob_ayoub_project.ui.screens.recipes

import android.text.Html
import android.text.Spanned
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.mob_ayoub_project.data.InfosFromOneRecipe
import com.example.mob_ayoub_project.data.Recipe
import com.example.mob_ayoub_project.data.Utils
import com.example.mob_ayoub_project.data.Utils.HtmlText
import com.example.mob_ayoub_project.models.CuisineViewModel
import com.example.mob_ayoub_project.models.RecipeDetailsViewModel
import com.example.mob_ayoub_project.models.Repository
import kotlinx.coroutines.launch


@Composable
fun DisplayRecipeChoosed(
    recipeId: Int? = null
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val recipeDetailsViewModel: RecipeDetailsViewModel = viewModel()

    val snackbarMessage by Repository.messageSnackBar.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(recipeId) {
        if (recipeId != null) {
            recipeDetailsViewModel.setRecipeChoosedId(recipeId)
        }
        recipeDetailsViewModel.fetchInfosFromRecipe()
    }

    LaunchedEffect(snackbarMessage) {
        if (snackbarMessage.isNotEmpty()) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(snackbarMessage)
            }
            Repository.updateMessageSnackBar("") // Clear the message after showing it
        }
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {

        recipeDetailsViewModel.resultsInfosFromOneRecipe.value?.image?.let { imageUrl ->
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
        recipeDetailsViewModel.resultsInfosFromOneRecipe.value?.title?.let { title ->
            Text(
                text = title,
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
        recipeDetailsViewModel.resultsInfosFromOneRecipe.value?.veryHealthy?.let { isHealthy ->
            val healthIndicator = if (isHealthy) "Healthy" else "Not very healthy"
            Text(
                text = healthIndicator,
                fontSize = 16.sp,
                color = if (isHealthy) Color.Green else Color.Red,
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
        recipeDetailsViewModel.resultsInfosFromOneRecipe.value?.summary?.let { summary ->
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
        recipeDetailsViewModel.resultsInfosFromOneRecipe.value?.extendedIngredients?.forEach { ingredient ->
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
        recipeDetailsViewModel.resultsInfosFromOneRecipe.value?.instructions?.let { instructions ->
            HtmlText(html = instructions.trimIndent())
        }
        Spacer(modifier = Modifier.height(30.dp))

        Row {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.weight(1f))
            Button(
                    onClick = {
                        recipeDetailsViewModel.resultsInfosFromOneRecipe.value?.let {
                            //mettre à jour dans le repository
                            Log.i("Recette favorite" , "bouton ajouté cliqué")
                            recipeDetailsViewModel.addFavoriteOrNot(it)
                        }
                    }
            ) {

            Text(text = "add Favorites")
            }
        }



    }


}

