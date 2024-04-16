package com.example.mob_ayoub_project.ui.screens.recipes

import android.inputmethodservice.Keyboard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mob_ayoub_project.R
import com.example.mob_ayoub_project.data.Cuisine
import com.example.mob_ayoub_project.data.Recipe
import com.example.mob_ayoub_project.ui.theme.Mob_Ayoub_ProjectTheme


@Composable
fun SelectCuisineScreen(
    cuisines: List<Cuisine>,
    onSelectionChanged: (Cuisine) -> Unit = {},
    modifier: Modifier = Modifier
){

    var selectedCuisine by rememberSaveable{ mutableStateOf<Cuisine?>(null) }

    Column(
        modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
    ) {
        cuisines.forEach{ item->

            Row (
                    modifier = Modifier.selectable(
                        selected = selectedCuisine == item,
                        onClick = {//quand on clique sur une cuisine ca execute la methode en paramètre
                            selectedCuisine = item
                            onSelectionChanged(item)
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ){

                Text(item.name)
            }
        }
    }
}

@Preview
@Composable
fun SelectCuisinePreview() {
   Mob_Ayoub_ProjectTheme {
        SelectCuisineScreen(
            cuisines = Cuisine.values().toList(),
            modifier = Modifier.fillMaxHeight()
        )
    }
}

