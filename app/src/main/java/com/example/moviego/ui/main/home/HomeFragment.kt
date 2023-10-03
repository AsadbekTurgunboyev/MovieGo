package com.example.moviego.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviego.R
import com.example.moviego.databinding.FragmentHomeBinding
import com.example.moviego.ui.main.home.adapter.PopularMoviesAdapter
import com.example.moviego.ui.main.home.adapter.TypeAdapter
import com.example.moviego.ui.main.home.viewmodel.PopularMovieViewModel
import com.example.moviego.ui.main.seeAll.SeeAllFragment
import com.example.moviego.utils.ResourceState
import org.koin.androidx.viewmodel.ext.android.viewModel


const val TOP_RATED = 1
const val POPULAR = 2
const val NOW_PLAYING = 3


class HomeFragment : Fragment() {


    lateinit var viewBinding: FragmentHomeBinding
    val list = arrayListOf<Int>()
    val popularMovieViewModel: PopularMovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        popularMovieViewModel.getPopularMovies()
        popularMovieViewModel.getTopRatedMovies()
        popularMovieViewModel.getNowPlayingMovies()

        with(viewBinding) {
            popularSeeAll.setOnClickListener { clickSeeAllButton(POPULAR) }

            topRatedSeeAll.setOnClickListener { clickSeeAllButton(TOP_RATED) }
            nowPlayingSeeAll.setOnClickListener { clickSeeAllButton(NOW_PLAYING) }
        }
        popularMovieViewModel.topRatedMovies.observe(viewLifecycleOwner) {

            when (it.state) {
                ResourceState.SUCCESS -> {
                    viewBinding.topRatedShimmer.stopShimmerAnimation()
                    viewBinding.topRatedShimmer.visibility = View.GONE
                    viewBinding.recyclerViewTopRated.adapter = it.data?.results?.let { it1 ->
                        PopularMoviesAdapter(
                            it1
                        )
                    }
                }

                ResourceState.LOADING -> {
                    viewBinding.topRatedShimmer.startShimmerAnimation()
                    viewBinding.topRatedShimmer.visibility = View.VISIBLE
                }

                ResourceState.ERROR -> {
                    viewBinding.topRatedShimmer.stopShimmerAnimation()
                    viewBinding.topRatedShimmer.visibility = View.GONE
                }
            }

        }

        popularMovieViewModel.popularMovies.observe(viewLifecycleOwner) {
            when (it.state) {

                ResourceState.SUCCESS -> {
                    viewBinding.popularShimmer.stopShimmerAnimation()
                    viewBinding.popularShimmer.visibility = View.GONE
                    viewBinding.recyclerViewPopular.adapter = it.data?.results?.let { it1 ->
                        PopularMoviesAdapter(
                            it1
                        )
                    }
                }

                ResourceState.LOADING -> {
                    viewBinding.popularShimmer.startShimmerAnimation()
                    viewBinding.popularShimmer.visibility = View.VISIBLE
                }

                ResourceState.ERROR -> {
                    viewBinding.popularShimmer.stopShimmerAnimation()
                    viewBinding.popularShimmer.visibility = View.GONE
                }
            }
        }

        popularMovieViewModel.nowPlayingMovies.observe(viewLifecycleOwner) {
            when (it.state) {
                ResourceState.SUCCESS -> {
                    viewBinding.nowPlayingShimmer.stopShimmerAnimation()
                    viewBinding.nowPlayingShimmer.visibility = View.GONE
                    viewBinding.recyclerViewNowPlaying.adapter = it.data?.results?.let { it1 ->
                        PopularMoviesAdapter(
                            it1
                        )
                    }
                }

                ResourceState.ERROR -> {
                    viewBinding.nowPlayingShimmer.stopShimmerAnimation()
                    viewBinding.nowPlayingShimmer.visibility = View.GONE
                }

                ResourceState.LOADING -> {
                    viewBinding.nowPlayingShimmer.startShimmerAnimation()
                    viewBinding.nowPlayingShimmer.visibility = View.VISIBLE
                }
            }
        }


        list.clear()
        list.add(R.drawable.tv)
        list.add(R.drawable.movie)
        list.add(R.drawable.shows)
        list.add(R.drawable.football)
        list.add(R.drawable.series)



        viewBinding.recyclerView.adapter = TypeAdapter(list)
    }

    private fun clickSeeAllButton(type: Int) {
        val intent = Intent(requireContext(), SeeAllFragment::class.java)
        intent.putExtra("see_all", type)
        startActivity(intent)

    }

}