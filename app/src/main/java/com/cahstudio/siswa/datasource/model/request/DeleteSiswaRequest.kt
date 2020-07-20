package com.cahstudio.siswa.datasource.model.request

import com.google.gson.annotations.SerializedName

data class DeleteSiswaRequest(
    @SerializedName("id") val id: Int
)