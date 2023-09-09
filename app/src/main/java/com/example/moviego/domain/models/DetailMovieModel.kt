package com.example.moviego.domain.models

data class DetailMovieModel(

    val id: Int,
    val backdrop_path: String,
    val budget: Int,
    val genres: List<Genres>,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_countries: List<PCountry>,
    val release_date: String,
    val runtime: Int,
    val tagline: String,
    val title: String,
    val vote_average: Double


    )

data class PCountry(
    val iso_3166_1: String,
    val name: String
)

data class Genres(
    val id: Int,
    val name: String
)