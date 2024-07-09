package com.junicodev.androidmaster.superheroapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/api/39f2d650a52eeffc3d50a96607661128/search/{name}")
    suspend fun getSuperheroes(@Path("name") superHeroName: String): Response<SuperHeroDataResponse>
}