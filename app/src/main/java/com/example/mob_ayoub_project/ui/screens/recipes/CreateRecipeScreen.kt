package com.example.mob_ayoub_project.ui.screens.recipes

import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.mob_ayoub_project.data.InfosFromOneRecipe
import com.example.mob_ayoub_project.data.Ingredients
import com.example.mob_ayoub_project.ui.theme.Mob_Ayoub_ProjectTheme
import java.util.jar.Manifest


@Composable
fun CreateRecipeScreen(
    modifier: Modifier,
   // onImageSelected : (Uri) -> Unit,
    onButtonClicked : (InfosFromOneRecipe) -> Unit = {}
    //onButtonFavoritesSelected :() -> Unit = {}
 ) {

        val scrollable = rememberScrollState()

        var recipeName by remember { mutableStateOf("") }
        var instructions by remember { mutableStateOf("") }
        var summary by remember { mutableStateOf("") }
        var ingredientValues by remember { mutableStateOf(List(7){Ingredients("")}) }




        Column(
            modifier = modifier.padding(16.dp).verticalScroll(scrollable),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {


            Row {
                Text(text = "Nom de la recette")
                TextField(
                    value = recipeName,
                    onValueChange = {recipeName  = it }
                )
            }
            Row {
                Text(text = "Infos de la recette  : ")
                TextField(
                    value = summary,
                    onValueChange = {summary  = it }
                )
            }
            Row {
                Text(text = "Instructions : ")
                TextField(
                    value = instructions,
                    onValueChange = {instructions = it }
                )
            }



            for (i in 0 until 7){

                Row {
                    Text(text = "Ingredient ${i+1}")
                    OutlinedTextField(
                        value = ingredientValues[i].name.toString() ,
                        onValueChange = {newValue ->
                            ingredientValues = ingredientValues.toMutableList().also {
                                it[i] = Ingredients(newValue)
                            }
                        },
                    )
                }

            }
            //TODO faire une Liste avec deux option pour very healty
            val recipe = InfosFromOneRecipe("",
                recipeName,
                true,
                summary,
                instructions,
                ingredientValues
            )
            Button(onClick = {
                onButtonClicked(recipe)
            }) {
                Text(text = "add Favorites")
            }

        }


}

/*val context = LocalContext.current
   val activityResultLauncher = rememberLauncherForActivityResult(
       contract = ActivityResultContracts.GetContent(),
       onResult = { uri: Uri? ->
           uri?.let { onImageSelected(it) }
       }
   )*/
/*Button(
           onClick = {
               if (ContextCompat.checkSelfPermission(
                       context,
                       android.Manifest.permission.READ_EXTERNAL_STORAGE
                   ) == PackageManager.PERMISSION_GRANTED
               ) {
                   // Ouvrir la galerie d'images pour sélectionner une photo
                   activityResultLauncher.launch("image/*")
               }
           }
       ) {
           Text("Importer une photo")
       }

        */
        */

