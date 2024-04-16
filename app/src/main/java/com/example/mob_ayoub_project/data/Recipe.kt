package com.example.mob_ayoub_project.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


/*
on utilise json pour mapper les noms des clés aux champs de la classe
 */
import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
     val id: Int,
     val title: String,
     val image: String,
     val imageType: String
)
