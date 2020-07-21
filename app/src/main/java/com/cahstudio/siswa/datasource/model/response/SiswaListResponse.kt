package com.cahstudio.siswa.datasource.model.response

import com.cahstudio.siswa.datasource.model.Siswa
import com.google.gson.annotations.SerializedName

data class SiswaListResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String?,
    @SerializedName("siswas") val siswaList: List<Siswa>
)