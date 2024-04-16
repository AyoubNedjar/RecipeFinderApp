package com.example.mob_ayoub_project.network.login

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
object AuthService {

    private const val baseURL = "https://dnsrivnxleeqdtbyhftv.supabase.co/"

    val authClient: AuthHTTPCient

       init{

            val jsonConverter = MoshiConverterFactory.create()
            val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
                .addConverterFactory(jsonConverter).baseUrl(baseURL)

            val retrofit: Retrofit = retrofitBuilder.build()
            authClient = retrofit.create(AuthHTTPCient::class.java)

       }
}