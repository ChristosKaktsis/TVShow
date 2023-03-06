package com.example.tvshow.network

import com.example.tvshow.database.DatabaseShow
import com.squareup.moshi.JsonClass

data class Image(val medium:String,val original:String)
data class Rating(val average:String?)
data class TVShow(
    val id:String, val thumbnail:String,val originalImage:String,val name:String,val rating: String, val summary:String
)

@JsonClass(generateAdapter = true)
data class TVShowContainer(val shows: List<NetworkTVShow>)

@JsonClass(generateAdapter = true)
data class NetworkTVShow(
    val id:String, val image:Image,val name:String,val rating: Rating, val summary:String)

/**
 * Convert Network results to database objects
 */
fun NetworkTVShow.asDomainModel():TVShow{
    return TVShow(id = id,
            thumbnail = image.medium,
            originalImage= image.original,
            name = name,
            rating = rating.average.toString(),
            summary = summary)
}
fun TVShowContainer.asDomainModel(): List<TVShow> {
    return shows.map {
        TVShow(id = it.id,
            thumbnail = it.image.medium,
            originalImage= it.image.original,
            name = it.name,
            rating = it.rating.average.toString(),
            summary = it.summary)
    }
}

/**
 * Convert Network results to database objects
 */
fun TVShowContainer.asDatabaseModel(): List<DatabaseShow> {
    return shows.map {
        DatabaseShow(id = it.id,
            thumbnail = it.image.medium,
            originalImage= it.image.original,
            name = it.name,
            rating = it.rating.average.toString(),
            summary = it.summary)
    }
}
