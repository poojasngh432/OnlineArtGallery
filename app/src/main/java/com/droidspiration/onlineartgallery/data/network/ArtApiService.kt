package com.droidspiration.onlineartgallery.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtApiService {

    @GET("public/collection/v1/search")
    suspend fun getRecords(
        @Query("q") query: String,
        @Query("hasImages") hasImages: Boolean = true
    ): RecordsResponse

    @GET("public/collection/v1/objects/{id}")
    suspend fun getArtPieceData(@Path("id") id: Int): ArtPieceData
}

object RetrofitInstance {
    private const val BASE_URL = "https://collectionapi.metmuseum.org/"

    val api: ArtApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ArtApiService::class.java)
    }
}