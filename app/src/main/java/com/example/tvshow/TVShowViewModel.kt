package com.example.tvshow

import android.app.Application
import androidx.lifecycle.*
import com.example.tvshow.database.getDatabase
import com.example.tvshow.network.TVShow
import com.example.tvshow.network.TVShowsApi
import com.example.tvshow.repository.TVShowsRepository
import kotlinx.coroutines.launch
import java.io.IOException

class TVShowViewModel(application: Application) : AndroidViewModel(application) {
    private val showRepository = TVShowsRepository(getDatabase(application))
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
    /**
     fun getTVShows(){
        viewModelScope.launch {
            try{
                _tvshows.value = TVShowsApi.retrofitService.getTVShows()
                _status.value = "Success: ${tvShows.value?.count()} tv shows"
            }
            catch (ex:Exception){
                println(ex)
                _status.value = "Failed"
            }
        }
    }
     **/
    fun onTVShowClicked(tvshow:TVShow){
        _tvshow.value = tvshow
    }

    /**
     * Factory for constructing DevByteViewModel with parameter
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TVShowViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return TVShowViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}