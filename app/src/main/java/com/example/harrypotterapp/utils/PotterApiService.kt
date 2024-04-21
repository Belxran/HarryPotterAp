package com.example.harrypotterapp.utils

import retrofit2.http.GET
import retrofit2.http.Path

interface PotterApiService {
    @GET("/{lang}/books")
    suspend fun getBooks(@Path("lang") lang: String): List<Book>

    @GET("/{lang}/characters")
    suspend fun getCharacters(@Path("lang") lang: String): List<Character>

    @GET("/{lang}/houses")
    suspend fun getHouses(@Path("lang") lang: String): List<House>
}
