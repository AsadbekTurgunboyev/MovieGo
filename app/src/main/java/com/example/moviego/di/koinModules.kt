package com.example.moviego.di

import com.example.moviego.data.repository.MainRepositoryImp
import com.example.moviego.domain.repository.MainRepository
import com.example.moviego.domain.usecase.GetMainResponseUseCase
import com.example.moviego.ui.main.detail.DetailMovieViewModel
import com.example.moviego.ui.main.home.viewmodel.PopularMovieViewModel
import com.example.moviego.ui.main.play.PlayMovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModules = module {
    viewModel{PopularMovieViewModel(get())}
    viewModel{DetailMovieViewModel(get())}
    viewModel{PlayMovieViewModel(get())}

    factory { GetMainResponseUseCase(mainRepository = get()) }
    single<MainRepository> {
        MainRepositoryImp(apiService = get())
    }
}