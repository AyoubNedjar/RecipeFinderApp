package com.example.mob_ayoub_project.ui.screens.recipes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mob_ayoub_project.R
import com.example.mob_ayoub_project.data.Cuisine
import com.example.mob_ayoub_project.models.CuisineViewModel


@Composable
fun SelectCuisineScreen(
    cuisines: List<Cuisine>,
    onSelectionChanged: (Cuisine) -> Unit = {}
) {

    val cuisineViewModel: CuisineViewModel = viewModel()
    var selectedCuisine by rememberSaveable { mutableStateOf<Cuisine?>(null) }


    Column(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_medium))
            .background(Color.White)

    ) {
        cuisines.forEachIndexed { index, item ->

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        onClick = { //when we click on a kitchen it executes the method as a parameter
                            selectedCuisine = item
                            onSelectionChanged(item)
                        }
                    )
                    .padding(5.dp)
                    .clip(RoundedCornerShape(percent = 50))
                    .shadow(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(percent = 50)
                    )
            ) {

                Text(
                    text = item.name,
                    color = if (selectedCuisine == item) Color.White else Color.Black,
                    modifier = Modifier
                        .padding(15.dp)
                        .align(Alignment.Center)
                )
            }
            if (index < cuisines.size - 1) {
                Spacer(modifier = Modifier.height(3.dp))
            }
        }
    }
}



