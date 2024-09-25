package com.droidspiration.onlineartgallery.ui

import com.droidspiration.onlineartgallery.data.network.ArtGalleryRepo
import javax.inject.Inject

class GetArtRecordsUseCase @Inject constructor(
    private val repository: ArtGalleryRepo
) {
    suspend operator fun invoke(query: String): Result<List<Int>> {
        var result: Result<List<Int>> = Result.Error(Exception("No data found"))

        repository.getRecords(query).collect { flowResult ->
            result = when (flowResult) {
                is Result.Success -> flowResult
                is Result.Error -> Result.Error(flowResult.exception)
                Result.Loading -> result // Keep previous result during loading
            }
        }
        return result
    }
}