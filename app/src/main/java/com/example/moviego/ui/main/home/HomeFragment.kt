package com.example.moviego.ui.main.home

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
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {


    lateinit var viewBinding: FragmentHomeBinding
    val list = arrayListOf<Int>()
    val popularMovieViewModel : PopularMovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        popularMovieViewModel.getPopularMovies()
        popularMovieViewModel.getTopRatedMovies()

        popularMovieViewModel.topRatedMovies.observe(viewLifecycleOwner){
            if (it != null){
                viewBinding.recyclerViewTopRated.adapter = PopularMoviesAdapter(it.results)
            }
        }

        popularMovieViewModel.popularMovies.observe(viewLifecycleOwner){
            if (it != null){
                viewBinding.recyclerViewPopular.adapter = PopularMoviesAdapter(it.results)
            }
        }


        list.add(R.drawable.tv)
        list.add(R.drawable.movie)
        list.add(R.drawable.shows)
        list.add(R.drawable.football)
        list.add(R.drawable.series)



        viewBinding.recyclerView.adapter = TypeAdapter(list)
    }

}