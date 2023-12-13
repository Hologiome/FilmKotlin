package com.example.premiereapplication

class TmdbMovieResult(
    var page: Int = 0,
    val results: List<TmdbMovie> = listOf())


class TmdbMovie(
    var overview: String = "",
    val release_date: String = "",
    val id: String = "",
    val title: String = "",
    val original_title: String = "",
    val backdrop_path: String? = "",
    val genre_ids: List<Int> = listOf(),
    val poster_path: String? = "") {
}

data class TmdbMovieDetail(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val belongs_to_collection: Any = Any(),
    val budget: Int = 0,
    val genres: List<Genre> = listOf(),
    val homepage: String = "",
    val id: Int = 0,
    val imdb_id: String = "",
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val production_companies: List<ProductionCompany> = listOf(),
    val production_countries: List<ProductionCountry> = listOf(),
    val release_date: String = "",
    val revenue: Int = 0,
    val runtime: Int = 0,
    val spoken_languages: List<SpokenLanguage> = listOf(),
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
)

data class Genre(
    val id: Int = 0,
    val name: String = ""
)

data class ProductionCompany(
    val id: Int = 0,
    val logo_path: String = "",
    val name: String = "",
    val origin_country: String = ""
)

data class ProductionCountry(
    val iso_3166_1: String = "",
    val name: String = ""
)

data class SpokenLanguage(
    val english_name: String = "",
    val iso_639_1: String = "",
    val name: String = ""
)

class TmdbSerieResult(
    var page: Int = 0,
    val results: List<TmdbSeries> = listOf())
class TmdbSeries(
    var name: String = "",
    val first_air_date: String = "",
    val id: String = "",
    val title: String = "",
    val original_title: String = "",
    val backdrop_path: String? = "",
    val genre_ids: List<Int> = listOf(),
    val poster_path: String? = "") {
}

//data class TMDBSerieDetails(
//    val id: Int,
//    val name: String,
//    val original_name: String,
//    val overview: String,
//    val genres: List<Genre>,
//    val first_air_date: String,
//    val last_air_date: String?,
//    val number_of_episodes: Int?,
//    val number_of_seasons: Int?,
//    val poster_path: String?,
//    val backdrop_path: String?,
//    val popularity: Double,
//    val vote_average: Double,
//    val vote_count: Int,
//    val status: String
//)

data class TMDBSerieDetails(
    val adult: Boolean = false,
    val backdropPath: String = "",
    val createdBy: List<Creator> = listOf(),
    val first_air_date: String = "",
    val genres: List<Genre> = listOf(),
    val homepage: String = "",
    val id: Int = 0,
    val languages: List<String> = listOf(),
    val lastAirDate: String = "",
    val lastEpisodeToAir: Episode? = null,
    val name: String = "",
    val networks: List<Network> = listOf(),
    val numberOfEpisodes: Int = 0,
    val numberOfSeasons: Int = 0,
    val originCountry: List<String> = listOf(),
    val originalLanguage: String = "",
    val originalName: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val productionCompanies: List<ProductionCompany> = listOf(),
    val productionCountries: List<ProductionCountry> = listOf(),
    val seasons: List<Season> = listOf(),
    val spokenLanguages: List<SpokenLanguage> = listOf(),
    val status: String = "",
    val tagline: String = "",
    val type: String = "",
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
)

data class Creator(
    val id: Int = 0,
    val name: String = "",
    val gender: Int = 0,
    val profilePath: String = ""
)

data class Episode(
    val id: Int = 0,
    val name: String = "",
    val overview: String = "",
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0,
    val airDate: String = "",
    val episodeNumber: Int = 0,
    val seasonNumber: Int = 0,
    val stillPath: String = ""
)

data class Network(
    val id: Int = 0,
    val name: String = "",
    val logoPath: String = "",
    val originCountry: String = ""
)


data class Season(
    val airDate: String = "",
    val episodeCount: Int = 0,
    val id: Int = 0,
    val name: String = "",
    val overview: String = "",
    val posterPath: String = "",
    val seasonNumber: Int = 0,
    val voteAverage: Double = 0.0
)

data class TmdbActorResult(
    val page: Int = 1,
    val results: List<TmdbActor> = listOf()
)

data class TmdbActor(
    val adult: Boolean = false,
    val id: String = "",
    val name: String = "",
    val originalName: String = "",
    val mediaType: String = "",
    val popularity: Double = 0.0,
    val gender: Int = 0,
    val knownForDepartment: String = "",
    val profile_path: String = "",
    val knownFor: List<Movie> = listOf()
)

data class Movie(
    val adult: Boolean = false,
    val backdropPath: String = "",
    val id: Int = 0,
    val title: String = "",
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val posterPath: String = "",
    val mediaType: String = "",
    val genreIds: List<Int> = listOf(),
    val popularity: Double = 0.0,
    val releaseDate: String = "",
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
)

data class TMDBActorDetails(
    val adult: Boolean = false,
    val alsoKnownAs: List<String> = listOf(),
    val biography: String = "",
    val birthday: String? = null,
    val deathday: String? = null,
    val gender: Int = 0,
    val homepage: String? = null,
    val id: Int = 0,
    val imdbId: String = "",
    val knownForDepartment: String = "",
    val name: String = "",
    val placeOfBirth: String? = null,
    val popularity: Double = 0.0,
    val profile_path: String = ""
)

data class TMDBCreditsResult(
    val cast: List<TMDBCastMember> = listOf()
)

data class TMDBCastMember(
    val adult: Boolean = false,
    val gender: Int = 0,
    val id: String = "",
    val knownForDepartment: String = "",
    val name: String = "",
    val originalName: String = "",
    val popularity: Double = 0.0,
    val profile_path: String = "",
    val castId: Int = 0,
    val character: String = "",
    val creditId: String = "",
    val order: Int = 0
)

data class TMDBDetailfull(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val belongs_to_collection: Any = Any(),
    val budget: Int = 0,
    val credits: Credits = Credits(),
    val genres: List<Genre> = listOf(),
    val homepage: String = "",
    val id: Int = 0,
    val imdb_id: String = "",
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val production_companies: List<ProductionCompany> = listOf(),
    val production_countries: List<ProductionCountry> = listOf(),
    val release_date: String = "",
    val revenue: Int = 0,
    val runtime: Int = 0,
    val spoken_languages: List<SpokenLanguage> = listOf(),
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
)

data class Credits(
    val cast: List<Cast> = listOf(),
    val crew: List<Crew> = listOf()
)



data class Cast(
    val adult: Boolean = false,
    val cast_id:  String = "",
    val character: String = "",
    val credit_id: String = "",
    val gender: Int = 0,
    val id: Int = 0,
    val known_for_department: String = "",
    val name: String = "",
    val order: Int = 0,
    val original_name: String = "",
    val popularity: Double = 0.0,
    val profile_path: String = ""
)

data class Crew(
    val adult: Boolean = false,
    val credit_id: String = "",
    val department: String = "",
    val gender: Int = 0,
    val id: Int = 0,
    val job: String = "",
    val known_for_department: String = "",
    val name: String = "",
    val original_name: String = "",
    val popularity: Double = 0.0,
    val profile_path: String = ""
)

