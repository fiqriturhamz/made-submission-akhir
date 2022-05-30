package com.dicoding.capstoneakhir.core.domain.usecase

import com.dicoding.capstoneakhir.core.domain.model.IMovie
import com.dicoding.capstoneakhir.core.domain.repository.IMovieRepository

class IMovieInteractor(private val iMovieRepository: IMovieRepository) : IMovieUseCase {

    override fun getList() = iMovieRepository.getList()

    override fun getWatchlist() = iMovieRepository.getWatchlist()

    override fun setWatchlist(iMovie: IMovie, state: Boolean) = iMovieRepository.setWatchlist(iMovie, state)

}