package com.dicoding.capstoneakhir.core.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.capstoneakhir.core.source.local.entity.IMovieEntity

@Database(entities = [IMovieEntity::class], version = 1, exportSchema = false)
abstract class IMovieDatabase : RoomDatabase() {

    abstract fun iMovieDAO(): IMovieDAO

}