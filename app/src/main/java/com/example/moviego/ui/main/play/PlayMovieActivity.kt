package com.example.moviego.ui.main.play

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.moviego.databinding.ActivityPlayMovieBinding
import com.example.moviego.utils.ResourceState
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayMovieBinding
    var moviKey = ""

    val viewModel: PlayMovieViewModel by viewModel()
    val b = object : AbstractYouTubePlayerListener() {

        override fun onReady(youTubePlayer: YouTubePlayer) {
            super.onReady(youTubePlayer)

        }
    }
    var movieId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieId = intent.getIntExtra("movie_id", 0)

        viewModel.getPlayMovie(movieId)

        viewModel.playMovies.observe(this){
            when(it.state){
                ResourceState.SUCCESS ->{
                    val key = it.data?.results?.get(0)?.key
                    moviKey = key.toString()
                }
                ResourceState.ERROR ->{}
                ResourceState.LOADING ->{}
            }
        }
        lifecycle.addObserver(binding.playYoutube)

        binding.playYoutube.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {

                Log.d("kalit", "onCreate: $moviKey")

                youTubePlayer.loadVideo(moviKey, 0f)
            }
        })

//        binding.playYoutube.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
//
//            override fun onReady(youTubePlayer: YouTubePlayer) {
//                super.onReady(youTubePlayer)
//               youTubePlayer.loadVideo("O-b2VfmmbyA", 0F)
//            }
//        })



    }


}