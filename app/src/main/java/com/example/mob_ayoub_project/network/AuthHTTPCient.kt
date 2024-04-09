package com.example.mob_ayoub_project.network

import com.example.mob_ayoub_project.data.DataPerson
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthHTTPCient {

    @Headers(
        "Content-Type: application/json",
        "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImRuc3Jpdm54bGVlcWR0YnloZnR2Iiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTY5NzE0MDI2MSwiZXhwIjoyMDEyNzE2MjYxfQ.jgJ49-c9Z8iPQnLVTnPlfRZpKwyBKht-OY8wMTceSiM"
    )
    @POST("/auth/v1/token?grant_type=password")
    suspend fun authenticate(@Body authRequest: DataPerson): TokenResponse
}