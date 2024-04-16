package com.example.mob_ayoub_project.ui.screens.recipes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mob_ayoub_project.ui.theme.Mob_Ayoub_ProjectTheme


@Composable
fun CreateRecipeScreen(
    modifier: Modifier,
    //onButtonFavoritesSelected :() -> Unit = {}
 ) {
    var recipeName by remember { mutableStateOf("") }
    var ingredient1 by remember { mutableStateOf(null) }
    var ingredient2 by remember { mutableStateOf(null) }
    var ingredient3 by remember { mutableStateOf(null) }
    var ingredient4 by remember { mutableStateOf(null) }
    var ingredient5 by remember { mutableStateOf(null) }
    var ingredient6 by remember { mutableStateOf(null) }
    var ingredient7 by remember { mutableStateOf(null) }

    var ingredientValues by remember { mutableStateOf(List(7) { "" }) }

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {


        Row {
            Text(text = "Nom de la recette")
            TextField(
                value = recipeName,
                onValueChange = {recipeName  = it }
            )
        }
        Button(
            onClick = { /* Ajouter la logique pour importer une photo */ }
        ) {
            Text("Importer une photo")
        }

        for (i in 0 until 7){

            Row {
                Text(text = "Ingredient ${i+1}")
                OutlinedTextField(
                    value = ingredientValues[i] ,
                    onValueChange = {newValue ->
                        ingredientValues = ingredientValues.toMutableList().also {
                            it[i] = newValue
                        }
                    },
                )
            }

        }

    }
}

@Preview
@Composable
fun CreateRecipePreview(){
    Mob_Ayoub_ProjectTheme {
        CreateRecipeScreen(
            modifier = Modifier.fillMaxSize()
        )
    }
}