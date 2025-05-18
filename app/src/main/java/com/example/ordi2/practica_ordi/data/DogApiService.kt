package com.example.ordi2.practica_ordi.data

import retrofit2.http.GET

data class DogResponse(
    val message: List<String>,
    val status: String
)

interface DogApiService {
    @GET("breeds/image/random/6")
    suspend fun getRandomDogImages(): DogResponse
} 