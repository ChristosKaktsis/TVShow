package com.example.tvshow.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvshow.network.NetworkTVShow
import com.example.tvshow.network.TVShow
import com.example.tvshow.network.TVShowsApi
import com.example.tvshow.network.asDomainModel
import kotlinx.coroutines.launch

class TVShowDetailViewModel: ViewModel() {
    private val _tvshow= MutableLiveData<TVShow>()
    val tvshow:LiveData<TVShow> = _tvshow
    fun getTVShow(id:String){
        viewModelScope.launch {
            try{
                _tvshow.value = TVShowsApi.retrofitService.getTVShow(id).asDomainModel()
            }
            catch (e:Exception){
                Log.d("error","${e.message}")
            }
        }
    }
}