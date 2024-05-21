package com.example.mob_ayoub_project.ui.screens.recipes


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.mob_ayoub_project.data.InfosFromOneRecipe
import com.example.mob_ayoub_project.data.Ingredients

@Composable
fun CreateRecipeScreen(
    modifier: Modifier,
    onButtonClicked : (InfosFromOneRecipe) -> Unit = {}
 ) {

        val scrollable = rememberScrollState()

        var recipeName by remember { mutableStateOf("") }
        var instructions by remember { mutableStateOf("") }
        var summary by remember { mutableStateOf("") }
        var ingredientValues by remember { mutableStateOf(List(7){Ingredients("")}) }
        var veryHealthy by remember { mutableStateOf(true) }

        val imageUrl = "https://img-3.journaldesfemmes.fr/mrK-0E6Jw7lGJUv9Y0mpK5yfCMg=/1500x/smart/0a6c4b8084be4b9d91265bbe65a5ba93/ccmcms-jdf/11437802.png"

        Column(
            modifier = modifier.padding(16.dp).verticalScroll(scrollable),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = "Recipe Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                LabeledTextField(label = "Nom de la recette", value = recipeName, onValueChange = { recipeName = it })
                LabeledTextField(label = "Infos de la recette", value = summary, onValueChange = { summary = it })
                LabeledTextField(label = "Instructions", value = instructions, onValueChange = { instructions = it })
            }



            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Sain ?: ")
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    RadioButton(
                        selected = veryHealthy,
                        onClick = { veryHealthy = true }
                    )
                    Text(
                        text = "Oui",
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                // Option Non
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    RadioButton(
                        selected = !veryHealthy,
                        onClick = { veryHealthy = false }
                    )
                    Text(
                        text = "Non",
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }



            for (i in 0 until 7){

                Row {
                    Text(text = "Ingredient ${i+1}  ")
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

            val recipe = InfosFromOneRecipe(
                imageUrl,
                recipeName,
                veryHealthy,
                summary,
                instructions,
                ingredientValues
            )
            Button(
                onClick = {
                    onButtonClicked(recipe)
                          },
                modifier= Modifier.align(Alignment.End)
                ) {
                Text(text = "add Favorites")
            }

        }


}
@Composable
fun LabeledTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            modifier = Modifier.width(120.dp)
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f)
        )
    }
}



