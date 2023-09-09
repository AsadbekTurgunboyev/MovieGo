package com.example.moviego.ui.main.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moviego.databinding.FragmentDetailFilmBinding
import com.example.moviego.di.IMAGE_URL
import com.example.moviego.domain.models.DetailMovieModel
import com.example.moviego.domain.models.ImagesMovieModel
import com.example.moviego.ui.main.home.adapter.ImagesAdapter
import com.example.moviego.utils.ResourceState
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFilmFragment : AppCompatActivity() {


    lateinit var viewBinding: FragmentDetailFilmBinding
    val viewModel: DetailMovieViewModel by viewModel()
    var movieId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = FragmentDetailFilmBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        movieId = intent.getIntExtra("movie_id", 0)

        viewModel.getDetailMovie(movieId)
        viewModel.getMovieImages(movieId)

        viewModel.detailMovie.observe(this){
            when(it.state){
                ResourceState.LOADING ->{}
                ResourceState.ERROR ->{
                }
                ResourceState.SUCCESS ->{
                    setUi(it.data)

                }
            }
        }

        viewModel.imagesMovie.observe(this){
            when(it.state){
                ResourceState.LOADING ->{}
                ResourceState.ERROR ->{
                }
                ResourceState.SUCCESS ->{
                    setImages(it.data)

                }
            }
        }
        Toast.makeText(this, "$movieId", Toast.LENGTH_SHORT).show()

    }

    private fun setImages(data: ImagesMovieModel?) {
        viewBinding.photosRecyclerview.adapter = data?.let { ImagesAdapter(it.backdrops) }
    }


    @OptIn(ExperimentalStdlibApi::class)
    private fun setUi(data: DetailMovieModel?) {
        val imageUrl = IMAGE_URL + data?.poster_path
        Glide.with(this).load(imageUrl).into(viewBinding.posterPath)


        var release_date: String = ""
        release_date += data?.release_date?.split("-")?.get(0) ?: ""

        for (ds in data?.production_countries!!){
            release_date += " ${ds.name}"
        }

        var genres = ""

        for (ds in 0..<data.genres.size){
            genres += if(ds == data.genres.size - 1){
                "${data.genres[ds].name}"
            }else{
                "${data.genres[ds].name}, "

            }
        }

        viewBinding.titleNameTextView.text = data?.title
        viewBinding.descriptionTextView.text = data?.overview
        viewBinding.ratingTextView.text = "IMDb ${data?.vote_average}/10"
        viewBinding.releaseDataTextView.text = release_date
        viewBinding.genresTextView.text = genres
    }
}
