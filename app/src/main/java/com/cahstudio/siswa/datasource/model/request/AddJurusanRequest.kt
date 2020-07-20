package com.cahstudio.siswa.datasource.model.request

import com.google.gson.annotations.SerializedName

data class AddJurusanRequest(
    @SerializedName("name") val name: String?
)