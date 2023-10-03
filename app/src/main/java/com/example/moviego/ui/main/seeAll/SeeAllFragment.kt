package com.example.moviego.ui.main.seeAll

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.moviego.R
import com.example.moviego.databinding.FragmentSeeAllBinding
import com.example.moviego.domain.models.PopularMoviesModels
import com.example.moviego.ui.main.home.NOW_PLAYING
import com.example.moviego.ui.main.home.POPULAR
import com.example.moviego.ui.main.home.TOP_RATED
import com.example.moviego.ui.main.home.viewmodel.PopularMovieViewModel
import com.example.moviego.utils.Resource
import com.example.moviego.utils.ResourceState
import org.koin.androidx.viewmodel.ext.android.viewModel

class SeeAllFragment : AppCompatActivity() {


    lateinit var viewBinding: FragmentSeeAllBinding
    val popularMovieViewModel: PopularMovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = FragmentSeeAllBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        when(intent.getIntExtra("see_all",1)){
            TOP_RATED -> {
                viewBinding.seeAllTitleTextView.text = "Top Rated"
                popularMovieViewModel.getTopRatedMovies()
                popularMovieViewModel.topRatedMovies.observe(this){
                    updateUi(it)
                }
            }
            POPULAR -> {
                viewBinding.seeAllTitleTextView.text = "Popular"

                popularMovieViewModel.getPopularMovies()
                popularMovieViewModel.popularMovies.observe(this){
                    updateUi(it)
                }
            }
            NOW_PLAYING ->{
                viewBinding.seeAllTitleTextView.text = "Now Playing"

                popularMovieViewModel.getNowPlayingMovies()
                popularMovieViewModel.nowPlayingMovies.observe(this){
                    updateUi(it)
                }
            }
        }

    }

    private fun updateUi(resource: Resource<PopularMoviesModels>?) {

        when(resource?.state){
            ResourceState.SUCCESS ->{
                viewBinding.recyclerViewSeeAll.adapter = resource.data?.results?.let {
                    SeeAllAdapter(
                        it
                    )
                }
            }
            ResourceState.ERROR ->{}
            ResourceState.LOADING ->{}
            else -> {}
        }
    }

}