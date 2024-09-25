package com.droidspiration.onlineartgallery.data.local

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Entity
data class ArtPiece(
    @PrimaryKey val id: Int
)

@Dao
interface ArtGalleryRecordsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecords(recordIds: List<Int>)

    @Query("SELECT * FROM ArtPiece ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomArtId(): ArtPiece
}

@Database(entities = [ArtPiece::class], version = 1)
abstract class ArtDatabase : RoomDatabase() {
    abstract fun artDao(): ArtGalleryRecordsDao
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): ArtDatabase {
        return Room.databaseBuilder(appContext, ArtDatabase::class.java, "art_db").build()
    }

    @Provides
    fun provideArtDao(db: ArtDatabase): ArtGalleryRecordsDao = db.artDao()
}