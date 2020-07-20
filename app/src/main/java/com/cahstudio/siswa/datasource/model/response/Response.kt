package com.cahstudio.siswa.datasource.model.response

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("success") val success: Boolean,
    @SerializedName("messsage") val message: String?
)