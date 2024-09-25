package com.droidspiration.onlineartgallery.ui

import com.droidspiration.onlineartgallery.data.network.ArtGalleryRepo
import com.droidspiration.onlineartgallery.data.network.ArtPieceData
import javax.inject.Inject

class GetRandomArtUseCase @Inject constructor(
    private val repository: ArtGalleryRepo
) {
    suspend operator fun invoke(): Result<ArtPieceData> {
        var result: Result<ArtPieceData> = Result.Error(Exception("No art piece found"))

        repository.getRandomArtData().collect { flowResult ->
            result = when (flowResult) {
                is Result.Success -> flowResult
                is Result.Error -> Result.Error(flowResult.exception)
                Result.Loading -> result // Keep previous result during loading
            }
        }
        return result
    }
}