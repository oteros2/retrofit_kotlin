package com.example.prueba_api.service

import com.example.prueba_api.model.MovieResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = "c76ed6d50b96d2bfc0920abaeade0be3",
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1
    ): Response<MovieResponse>
}

object MovieApiServiceFactory{
    fun makeMovieApiService(): MovieApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)
    }
}