package com.example.mob_ayoub_project.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


/*
This class represents recipes from a chosen cuisine.
*/
data class Recipe(
     val id: Int,
     val title: String,
     val image: String,
     val imageType: String
)
