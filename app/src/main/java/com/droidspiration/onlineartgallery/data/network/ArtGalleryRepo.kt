package com.droidspiration.onlineartgallery.data.network

import com.droidspiration.onlineartgallery.data.local.ArtGalleryRecordsDao
import com.droidspiration.onlineartgallery.data.local.ArtPiece
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton
import com.droidspiration.onlineartgallery.ui.Result

@Singleton
class ArtGalleryRepo @Inject constructor(
    private val artApiService: ArtApiService,
    private val artGalleryRecordsDao: ArtGalleryRecordsDao
) {
    suspend fun getRecords(query: String): Flow<Result<List<Int>>> = flow {
        emit(Result.Loading)
        try {
            val response = artApiService.getRecords(query)
            val records = response.records
            val objectIDs = records.map { ArtPiece(objectId = it) }
            artGalleryRecordsDao.insertRecords(objectIDs)
            emit(Result.Success(records))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    suspend fun getRandomArtData(): Flow<Result<ArtPieceData>> = flow {
        emit(Result.Loading)
        try {
            val randomRecord = artGalleryRecordsDao.getRandomArtId()
            val artPieceData = artApiService.getArtPieceData(randomRecord)
            emit(Result.Success(artPieceData))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}