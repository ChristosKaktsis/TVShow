package com.example.tvshow.network

import com.example.tvshow.database.DatabaseShow
import com.squareup.moshi.JsonClass

data class Image(val medium:String,val original:String)
data class Rating(val average:String?)
data class TVShow(
    val id:String, val image:Image,val name:String,val rating: Rating, val summary:String
)

@JsonClass(generateAdapter = true)
data class TVShowContainer(val shows: List<NetworkTVShow>)

@JsonClass(generateAdapter = true)
data class NetworkTVShow(
    val id:String, val image:Image,val name:String,val rating: Rating, val summary:String)

/**
 * Convert Network results to database objects
 */
fun TVShowContainer.asDomainModel(): List<TVShow> {
    return shows.map {
        TVShow(id = it.id,
            image = it.image,
            name = it.name,
            rating = it.rating,
            summary = it.summary)
    }
}

/**
 * Convert Network results to database objects
 */
fun TVShowContainer.asDatabaseModel(): List<DatabaseShow> {
    return shows.map {
        DatabaseShow(id = it.id,
            image = it.image.medium,
            name = it.name,
            rating = it.rating.average,
            summary = it.summary)
    }
}
