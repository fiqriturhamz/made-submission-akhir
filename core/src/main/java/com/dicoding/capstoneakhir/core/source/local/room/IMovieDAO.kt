package com.dicoding.capstoneakhir.core.source.local.room

import androidx.room.*
import com.dicoding.capstoneakhir.core.source.local.entity.IMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IMovieDAO {

    @Query("SELECT * FROM movee")
    fun getList(): Flow<List<IMovieEntity>>

    @Query("SELECT * FROM movee WHERE watchlist = 1")
    fun getWatchlist(): Flow<List<IMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatchlist(movies: List<IMovieEntity>)

    @Update
    fun updateWatchlist(entity: IMovieEntity)

}