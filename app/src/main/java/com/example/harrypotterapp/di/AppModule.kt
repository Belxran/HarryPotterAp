package com.example.harrypotterapp.di

import com.example.harrypotterapp.utils.PotterApiService
import com.example.harrypotterapp.utils.House
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://potterapi-fedeperin.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providePotterApiService(retrofit: Retrofit): PotterApiService {
        return retrofit.create(PotterApiService::class.java)
    }

    @Singleton
    @Provides
    suspend fun provideHouseList(apiService: PotterApiService): List<House> {
        return withContext(Dispatchers.IO) {
            apiService.getHouses("en")
        }
    }
}
