package com.example.mob_ayoub_project.network

data class TokenResponse(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int
)
