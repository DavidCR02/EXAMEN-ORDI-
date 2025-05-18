package com.example.ordi2.practica_ordi.data

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogRepository {
    private val api: DogApiService = Retrofit.Builder()
        .baseUrl("https://dog.ceo/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DogApiService::class.java)

    suspend fun getDogImages(): List<String> {
        return try {
            Log.d("DogRepository", "Llamando a la API de perros...")
            val response = api.getRandomDogImages()
            Log.d("DogRepository", "Respuesta recibida: ${response.message}")
            response.message
        } catch (e: Exception) {
            Log.e("DogRepository", "Error en la llamada a la API: ${e.message}", e)
            emptyList()
        }
    }
}
