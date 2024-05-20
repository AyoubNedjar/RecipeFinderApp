package com.example.mob_ayoub_project.network.login

import com.example.mob_ayoub_project.data.Utils
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
object AuthService {


    val authClient: AuthHTTPCient

       init{

            val jsonConverter = MoshiConverterFactory.create()
            val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
                .addConverterFactory(jsonConverter).baseUrl(Utils.baseURLLogin)

            val retrofit: Retrofit = retrofitBuilder.build()
            authClient = retrofit.create(AuthHTTPCient::class.java)

       }
}