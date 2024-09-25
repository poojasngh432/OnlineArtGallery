package com.droidspiration.onlineartgallery.data.network

data class ArtPieceData(
    val objectID: Int,
    val name: String,
    val primaryImage: String,
    val title: String,
    val artistDisplayName: String,
    val artistDisplayBio: String,
    val department: String
)
