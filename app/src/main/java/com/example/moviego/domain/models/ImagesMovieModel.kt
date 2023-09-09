package com.example.moviego.domain.models

data class ImagesMovieModel(

    val backdrops : List<BackDrops>
)

data class BackDrops(
    val file_path: String
)
