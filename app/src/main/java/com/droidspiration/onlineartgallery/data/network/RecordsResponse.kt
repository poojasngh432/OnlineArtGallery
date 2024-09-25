package com.droidspiration.onlineartgallery.data.network

import com.google.gson.annotations.SerializedName

data class RecordsResponse(
    val name: String,
    @SerializedName("objectIDs") val records: List<Int>
)
