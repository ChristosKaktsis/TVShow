package com.example.tvshow.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShowDao {
    @Query("select * from databaseshow")
    fun getShows(): LiveData<List<DatabaseShow>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( videos: List<DatabaseShow>)
}



@Database(entities = [DatabaseShow::class], version = 1)
abstract class TVShowsDatabase: RoomDatabase() {
    abstract val showDao: ShowDao
}

private lateinit var INSTANCE: TVShowsDatabase

fun getDatabase(context: Context): TVShowsDatabase {
    synchronized(TVShowsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                TVShowsDatabase::class.java,
                "shows").build()
        }
    }
    return INSTANCE
}