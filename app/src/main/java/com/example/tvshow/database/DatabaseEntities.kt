package com.example.tvshow.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tvshow.network.Image
import com.example.tvshow.network.Rating
import com.example.tvshow.network.TVShow

@Entity
data class DatabaseShow constructor(
    @PrimaryKey
    val id:String,
    val image: String,
    val name:String,
    val rating: String?,
    val summary:String)

/**
 * Map DatabaseVideos to domain entities
 */
fun List<DatabaseShow>.asDomainModel(): List<TVShow> {
    return map {
        TVShow(
            id = it.id,
            image = Image(it.image,it.image),
            name = it.name,
            rating = Rating(it.rating),
            summary = it.summary)
    }
}
