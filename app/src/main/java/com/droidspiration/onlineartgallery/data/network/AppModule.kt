package com.droidspiration.onlineartgallery.data.network

import android.content.Context
import androidx.room.Room
import com.droidspiration.onlineartgallery.data.local.ArtDatabase
import com.droidspiration.onlineartgallery.data.local.ArtGalleryRecordsDao
import com.droidspiration.onlineartgallery.ui.GetArtRecordsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {
    private const val BASE_URL = "https://collectionapi.metmuseum.org/"

    @Provides
    @Singleton
    fun provideArtApiService(): ArtApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ArtApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideArtGalleryRepository(
        api: ArtApiService,
        dao: ArtGalleryRecordsDao
    ): ArtGalleryRepo = ArtGalleryRepo(api, dao)

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ): ArtDatabase = Room.databaseBuilder(
        appContext,
        ArtDatabase::class.java,
        "art_db"
    ).build()

    @Provides
    fun provideArtDao(db: ArtDatabase): ArtGalleryRecordsDao = db.artDao()

    @Provides
    fun provideArtRecordsUseCase(repository: ArtGalleryRepo): GetArtRecordsUseCase = GetArtRecordsUseCase(repository)
}