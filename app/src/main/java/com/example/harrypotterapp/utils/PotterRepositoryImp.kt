package com.example.harrypotterapp.utils



class PotterRepositoryImpl(private val potterApiService: PotterApiService) : PotterRepository {

    override suspend fun getBooks(lang: String): List<Book> {
        return potterApiService.getBooks(lang)
    }

    override suspend fun getCharacters(lang: String): List<Character> {
        return potterApiService.getCharacters(lang)
    }

    override suspend fun getHouses(lang: String): List<House> {
        return potterApiService.getHouses(lang)
    }
}
