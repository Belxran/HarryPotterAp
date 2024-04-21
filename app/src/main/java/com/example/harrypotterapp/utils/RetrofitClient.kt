package com.example.harrypotterapp.utils

import com.example.harrypotterapp.ui.libros.DateTypeAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date

object RetrofitClient {
    private const val BASE_URL = "https://potterapi-fedeperin.vercel.app/"

    private val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, DateTypeAdapter())
        .create()


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val service = retrofit.create(PotterApiService::class.java)
}

suspend fun fetchBooks(lang: String): List<Book> {
    return withContext(Dispatchers.IO) {
        RetrofitClient.service.getBooks(lang)
    }
}

suspend fun fetchCharacters(lang: String): List<Character> {
    return withContext(Dispatchers.IO) {
        RetrofitClient.service.getCharacters(lang)
    }
}

suspend fun fetchHouses(lang: String): List<House> {
    return withContext(Dispatchers.IO) {
        RetrofitClient.service.getHouses(lang)
    }
}
