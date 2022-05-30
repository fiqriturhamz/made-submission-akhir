package com.dicoding.capstoneakhir.di

import com.dicoding.capstoneakhir.core.domain.usecase.IMovieInteractor
import com.dicoding.capstoneakhir.core.domain.usecase.IMovieUseCase
import com.dicoding.capstoneakhir.ui.details.DetailsViewModel
import com.dicoding.capstoneakhir.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<IMovieUseCase> {
        IMovieInteractor(get())
    }
}

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }

    viewModel {
        DetailsViewModel(get())
    }
}