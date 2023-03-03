package com.example.tvshow.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.tvshow.database.TVShowsDatabase
import com.example.tvshow.database.asDomainModel
import com.example.tvshow.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TVShowsRepository(private val database: TVShowsDatabase) {
    val videos: LiveData<List<TVShow>> = Transformations.map(database.showDao.getShows()) {
        it.asDomainModel()
    }

    suspend fun refreshShows() {
        withContext(Dispatchers.IO) {
            val showlist = TVShowsApi.retrofitService.getTVShows()
            val container = TVShowContainer(showlist)
            database.showDao.insertAll(container.asDatabaseModel())
        }
    }
}