package com.example.tvshow.ui

import android.app.Application
import androidx.lifecycle.*
import com.example.tvshow.database.getDatabase
import com.example.tvshow.network.TVShow
import com.example.tvshow.repository.TVShowsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class TVShowViewModel @Inject constructor(private val showRepository:TVShowsRepository) :  ViewModel(){
    //private val showRepository = TVShowsRepository(getDatabase(application))
    private val _status = MutableLiveData<String>()
    private val _tvshows = MutableLiveData<List<TVShow>>()
    val tvShows: LiveData<List<TVShow>> = showRepository.videos
    val status:LiveData<String> = _status
    private val _tvshow = MutableLiveData<TVShow>()
    val tvshow: LiveData<TVShow> = _tvshow
    init {
        refreshDataFromRepository()
    }
    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                showRepository.refreshShows()
            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
            }
        }
    }
    fun onTVShowClicked(tvshow:TVShow){
        _tvshow.value = tvshow
    }
}