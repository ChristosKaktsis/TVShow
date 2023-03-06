package com.example.tvshow.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL =
    "https://api.tvmaze.com/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface TVShowApiService{
    @GET("shows")
    suspend fun getTVShows(): List<NetworkTVShow>
    @GET("shows/{id}")
    suspend fun getTVShow(@Path("id") id:String): NetworkTVShow
}
object TVShowsApi{
    val retrofitService : TVShowApiService by lazy{
        retrofit.create(TVShowApiService::class.java)
    }
}