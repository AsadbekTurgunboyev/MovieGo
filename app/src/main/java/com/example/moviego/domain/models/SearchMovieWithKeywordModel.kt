package com.example.moviego.domain.models

data class SearchMovieWithKeywordModel(
    val page: Int,
    val results: List<MovieKeyword>,
    val total_pages: Int,
    val total_results: Int
)

data class MovieKeyword(
    val id: Int,
    val name: String
)