package com.dicoding.capstoneakhir.core.source

import com.dicoding.capstoneakhir.core.domain.model.IMovie
import com.dicoding.capstoneakhir.core.domain.repository.IMovieRepository
import com.dicoding.capstoneakhir.core.source.local.LocalDataSource
import com.dicoding.capstoneakhir.core.source.remote.RemoteDataSource
import com.dicoding.capstoneakhir.core.source.remote.network.APIResponse
import com.dicoding.capstoneakhir.core.source.remote.response.Response
import com.dicoding.capstoneakhir.core.utils.AppExecutors
import com.dicoding.capstoneakhir.core.utils.DataMapper.mapDomainToEntity
import com.dicoding.capstoneakhir.core.utils.DataMapper.mapEntitiesToDomain
import com.dicoding.capstoneakhir.core.utils.DataMapper.mapResponseToEntities
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class IMovieRepository(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource, private val appExecutors: AppExecutors) :
    IMovieRepository {

    override fun getList(): Flow<Resource<List<IMovie>>> {
        return object : NetworkBoundResource<List<IMovie>, List<Response>>() {
            override fun loadFromDB(): Flow<List<IMovie>> {
                return localDataSource.getList().map {
                    mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<IMovie>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<APIResponse<List<Response>>> = remoteDataSource.getList()

            override suspend fun saveCallResult(data: List<Response>) {
                mapResponseToEntities(data).also {
                    localDataSource.insertWatchlist(it)
                }
            }
        }.asFlow()
    }

    override fun getWatchlist(): Flow<List<IMovie>> {
        return localDataSource.getWatchlist().map {
            mapEntitiesToDomain(it)
        }
    }

    override fun setWatchlist(iMovie: IMovie, state: Boolean) {
        mapDomainToEntity(iMovie).also {
            appExecutors.diskIO().execute {
                localDataSource.setWatchlist(it, state)
            }
        }
    }

}