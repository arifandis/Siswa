package com.cahstudio.siswa.datasource.network

import com.cahstudio.siswa.datasource.model.request.AddSiswaRequest
import com.cahstudio.siswa.datasource.model.request.DeleteSiswaRequest
import com.cahstudio.siswa.datasource.model.request.UpdateSiswaRequest
import com.cahstudio.siswa.datasource.model.response.ChartRespose
import com.cahstudio.siswa.datasource.model.response.Response
import com.cahstudio.siswa.datasource.model.response.SiswaListResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiEndPoint {
    @POST("siswa/add")
    fun addSiswa(@Body request: AddSiswaRequest): Call<Response>

    @POST("siswa/update")
    fun updateSiswa(@Body request: UpdateSiswaRequest): Call<Response>

    @POST("siswa/delete")
    fun deleteSiswa(@Body request: DeleteSiswaRequest): Call<Response>

    @GET("siswa/all_siswa")
    fun allSiswa(): Call<SiswaListResponse>

    @POST("jurusan/add")
    fun addJurusan(@Body request: AddSiswaRequest): Call<Response>

    @POST("jurusan/update")
    fun updateJurusan(@Body request: UpdateSiswaRequest): Call<Response>

    @POST("jurusan/delete")
    fun deleteJurusan(@Body request: DeleteSiswaRequest): Call<Response>

    @GET("jurusan/all_siswa")
    fun allJurusan(): Call<SiswaListResponse>

    @GET("siswa/chart")
    fun chart(): Call<ChartRespose>
}