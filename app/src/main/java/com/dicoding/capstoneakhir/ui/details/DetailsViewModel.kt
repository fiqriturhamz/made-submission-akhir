package com.dicoding.capstoneakhir.ui.details

import androidx.lifecycle.ViewModel
import com.dicoding.capstoneakhir.core.domain.model.IMovie
import com.dicoding.capstoneakhir.core.domain.usecase.IMovieUseCase

class DetailsViewModel(private val iMovieUseCase: IMovieUseCase) : ViewModel() {

    fun watchlist(IMovie: IMovie, newState: Boolean) = iMovieUseCase.setWatchlist(IMovie, newState)

}