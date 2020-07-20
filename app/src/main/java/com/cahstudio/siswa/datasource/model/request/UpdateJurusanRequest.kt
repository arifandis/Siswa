package com.cahstudio.siswa.datasource.model.request

import com.google.gson.annotations.SerializedName

data class UpdateJurusanRequest(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String?
)