package com.dicoding.capstoneakhir.watchlist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.capstoneakhir.core.domain.usecase.IMovieUseCase

class WatchlistViewModel(iMovieUseCase: IMovieUseCase) : ViewModel() {

    val moviesWatchlist = iMovieUseCase.getWatchlist().asLiveData()

}