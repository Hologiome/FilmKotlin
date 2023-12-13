package com.example.premiereapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    val api = retrofit.create(Api::class.java)
    val movies = MutableStateFlow<List<TmdbMovie>>(listOf())

    fun getMovies() {
        viewModelScope.launch {
            movies.value = api.lastmovies("c36d23110d3cb6185a16058a84974221").results
        }
    }

    val details = MutableStateFlow(TmdbMovieDetail())
    fun getMovieById(movieId: String) {
        Log.d("uuuuu", movieId)
        viewModelScope.launch {
            details.value = api.detailmovie(api_key = "c36d23110d3cb6185a16058a84974221", movie_id = movieId)
        }
    }

    val series = MutableStateFlow<List<TmdbSeries>>(listOf())

    fun getSerie() {
        viewModelScope.launch {
            series.value = api.lastseries("c36d23110d3cb6185a16058a84974221").results
        }
    }

    val detailsSerie = MutableStateFlow(TMDBSerieDetails())
    fun getSerieById(serieId: String) {
        Log.d("uuuuu", serieId)
        viewModelScope.launch {
            detailsSerie.value = api.detailserie(api_key = "c36d23110d3cb6185a16058a84974221", serie_id = serieId)
        }
    }

    val actors = MutableStateFlow<List<TmdbActor>>(listOf())

    fun getActors() {
        viewModelScope.launch {
            actors.value = api.lastactors("c36d23110d3cb6185a16058a84974221").results
        }
    }

    val detailsActor = MutableStateFlow(TMDBActorDetails())
    fun getActorById(actorId: String) {
        Log.d("uuuuu", actorId)
        viewModelScope.launch {
            detailsActor.value = api.detailactor(api_key = "c36d23110d3cb6185a16058a84974221", actor_id = actorId)
        }
    }

}