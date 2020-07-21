package com.cahstudio.siswa.datasource.model.request

import com.google.gson.annotations.SerializedName

data class UpdateSiswaRequest(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("gender") val gender: String?,
    @SerializedName("jurusan_id") val jurusan: String? = null
)