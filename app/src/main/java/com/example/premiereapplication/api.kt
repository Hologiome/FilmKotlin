package com.example.premiereapplication

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("trending/movie/week")
    suspend fun lastmovies(@Query("api_key") api_key: String): TmdbMovieResult

    @GET("movie/{movie_id}")
    suspend fun detailmovie( @Path("movie_id") movie_id: String, @Query("api_key") api_key: String): TmdbMovieDetail

    @GET("trending/tv/week")
    suspend fun lastseries(@Query("api_key") api_key: String): TmdbSerieResult

    @GET("tv/{serie_id}")
    suspend fun detailserie( @Path("serie_id") serie_id: String, @Query("api_key") api_key: String): TMDBSerieDetails

    @GET("trending/person/week")
    suspend fun lastactors(@Query("api_key") api_key: String): TmdbActorResult

    @GET("person/{actor_id}")
    suspend fun detailactor( @Path("actor_id") actor_id: String, @Query("api_key") api_key: String): TMDBActorDetails

    @GET("movie/{movie_id}?append_to_response=credits")
    suspend fun lastcastmembers( @Path("movie_id") movie_id: String, @Query("api_key") api_key: String): TMDBCreditsResult
}