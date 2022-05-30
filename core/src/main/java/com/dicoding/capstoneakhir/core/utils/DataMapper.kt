package com.dicoding.capstoneakhir.core.utils

import com.dicoding.capstoneakhir.core.domain.model.IMovie
import com.dicoding.capstoneakhir.core.source.local.entity.IMovieEntity
import com.dicoding.capstoneakhir.core.source.remote.response.Response

object DataMapper {

    fun mapResponseToEntities(input: List<Response>): List<IMovieEntity> {
        val iMovieEntity = ArrayList<IMovieEntity>()

        input.map { response ->
            response.apply {
                IMovieEntity(
                    id = id,
                    backdrop = backdrop,
                    poster = poster,
                    title = title,
                    releaseDate = releaseDate,
                    rating = rating,
                    synopsis = synopsis,
                    watchlist = false
                ).also {
                    iMovieEntity.add(it)
                }
            }
        }

        return iMovieEntity
    }

    fun mapEntitiesToDomain(input: List<IMovieEntity>): List<IMovie> {
        return input.map {
            IMovie(
                id = it.id,
                backdrop = it.backdrop,
                poster = it.poster,
                title = it.title,
                releaseDate = it.releaseDate,
                rating = it.rating,
                synopsis = it.synopsis,
                watchlist = it.watchlist
            )
        }
    }

    fun mapDomainToEntity(input: IMovie) = IMovieEntity(
        id = input.id,
        backdrop = input.backdrop,
        poster = input.poster,
        title = input.title,
        releaseDate = input.releaseDate,
        rating = input.rating,
        synopsis = input.synopsis,
        watchlist = input.watchlist
    )

}