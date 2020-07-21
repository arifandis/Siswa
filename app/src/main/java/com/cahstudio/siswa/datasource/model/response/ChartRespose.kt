package com.cahstudio.siswa.datasource.model.response

import com.cahstudio.siswa.datasource.model.Jurusan
import com.google.gson.annotations.SerializedName

data class ChartRespose(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String?,
    @SerializedName("men") val men: Int,
    @SerializedName("women") val women: Int
)