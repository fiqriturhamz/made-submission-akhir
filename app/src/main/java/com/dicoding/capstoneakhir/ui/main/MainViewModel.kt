package com.dicoding.capstoneakhir.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.capstoneakhir.core.domain.usecase.IMovieUseCase

class MainViewModel(iMovieUseCase: IMovieUseCase) : ViewModel() {

    val iMovie = iMovieUseCase.getList().asLiveData()

}