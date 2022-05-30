package com.dicoding.capstoneakhir.core.source.local

import com.dicoding.capstoneakhir.core.source.local.entity.IMovieEntity
import com.dicoding.capstoneakhir.core.source.local.room.IMovieDAO
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val iMovieDAO: IMovieDAO) {

    fun getList(): Flow<List<IMovieEntity>> = iMovieDAO.getList()

    fun getWatchlist(): Flow<List<IMovieEntity>> = iMovieDAO.getWatchlist()

    suspend fun insertWatchlist(list: List<IMovieEntity>) = iMovieDAO.insertWatchlist(list)

    fun setWatchlist(iMovieEntity: IMovieEntity, newState: Boolean) {
        iMovieEntity.apply {
            watchlist = newState
            iMovieDAO.updateWatchlist(this)
        }
    }

}