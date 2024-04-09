package com.example.mob_ayoub_project.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
object AuthService {

    private const val baseURL = "https://dnsrivnxleeqdtbyhftv.supabase.co/"//ne changera jamais
    val authClient : AuthHTTPCient

    init {
        // create a converter JSON-> Kotlin
        val jsonConverter = MoshiConverterFactory.create()
        val retrofitBuilder : Retrofit.Builder = Retrofit.Builder()
            .addConverterFactory(jsonConverter) .baseUrl(baseURL)

        val retrofit : Retrofit = retrofitBuilder.build()
        authClient   = retrofit.create(AuthHTTPCient::class.java)
    }
}