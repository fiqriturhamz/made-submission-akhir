package com.dicoding.capstoneakhir.core.domain.repository

import com.dicoding.capstoneakhir.core.domain.model.IMovie
import com.dicoding.capstoneakhir.core.source.Resource
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getList(): Flow<Resource<List<IMovie>>>

    fun getWatchlist(): Flow<List<IMovie>>

    fun setWatchlist(iMovie: IMovie, state: Boolean)

}