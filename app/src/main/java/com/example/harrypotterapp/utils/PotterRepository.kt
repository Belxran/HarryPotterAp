package com.example.harrypotterapp.utils


interface PotterRepository {
    suspend fun getBooks(lang: String): List<Book>
    suspend fun getCharacters(lang: String): List<Character>
    suspend fun getHouses(lang: String): List<House>
}
