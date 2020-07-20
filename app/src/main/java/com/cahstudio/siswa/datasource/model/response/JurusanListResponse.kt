package com.cahstudio.siswa.datasource.model.response

import com.cahstudio.siswa.datasource.model.Jurusan
import com.cahstudio.siswa.datasource.model.Siswa
import com.google.gson.annotations.SerializedName

data class JurusanListResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("messsage") val message: String?,
    @SerializedName("jurusans") val jurusanList: List<Jurusan>
)