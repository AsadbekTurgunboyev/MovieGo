package com.example.moviego.domain.models

data class PlayMovieModel(
    val id:Int,
    val results: List<PlayDetail>
)
data class PlayDetail(
    val name: String,
    val key: String
//{
//    "iso_639_1": "en",
//    "iso_3166_1": "US",
//    "name": "Fight Club (1999) Trailer - Starring Brad Pitt, Edward Norton, Helena Bonham Carter",
//    "key": "O-b2VfmmbyA",
//    "site": "YouTube",
//    "size": 720,
//    "type": "Trailer",
//    "official": false,
//    "published_at": "2016-03-05T02:03:14.000Z",
//    "id": "639d5326be6d88007f170f44"
//},
)