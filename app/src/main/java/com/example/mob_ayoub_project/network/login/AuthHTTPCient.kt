package com.example.mob_ayoub_project.network.login

import com.example.mob_ayoub_project.data.DataPerson
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import com.example.mob_ayoub_project.data.Utils


interface AuthHTTPCient {

    @Headers(
        "Content-Type: application/json",
        "apikey: ${Utils.apiKeyLogin}"
    )
    /*
    Send request with parameters for recive the response
     */
    @POST("/auth/v1/token?grant_type=password")
    suspend fun authenticate(@Body authRequest: DataPerson): TokenResponse
}
