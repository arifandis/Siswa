package com.cahstudio.siswa.datasource.model.request

import com.google.gson.annotations.SerializedName

data class AddSiswaRequest(
    @SerializedName("name") val name: String?,
    @SerializedName("gender") val gender: String?,
    @SerializedName("jurusan") val jurusan: String? = null
)