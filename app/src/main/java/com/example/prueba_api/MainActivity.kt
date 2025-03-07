package com.example.prueba_api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.prueba_api.components.MovieCard
import com.example.prueba_api.model.Result
import com.example.prueba_api.service.MovieApiServiceFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieApiService = MovieApiServiceFactory.makeMovieApiService()

        enableEdgeToEdge()
        setContent {
            var movies by remember { mutableStateOf<List<Result>>(emptyList()) }

            LaunchedEffect(Unit) {
                try {
                    movies = movieApiService.getNowPlayingMovies(
                    ).body()?.results ?: emptyList()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(60.dp),
                verticalArrangement = Arrangement.spacedBy(50.dp)
            ) {
                items(movies) { movie ->
                    MovieCard(movie = movie)
                }
            }
        }
    }
}