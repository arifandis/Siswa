package com.cahstudio.siswa.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cahstudio.siswa.R
import com.cahstudio.siswa.adapter.SiswaAdapter
import com.cahstudio.siswa.datasource.model.Siswa
import com.cahstudio.siswa.datasource.model.request.DeleteSiswaRequest
import com.cahstudio.siswa.datasource.model.response.SiswaListResponse
import com.cahstudio.siswa.datasource.network.ApiEndPoint
import com.cahstudio.siswa.datasource.network.NetworkConfig
import kotlinx.android.synthetic.main.activity_siswa.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SiswaActivity : AppCompatActivity() {
    private lateinit var mApi: ApiEndPoint
    private lateinit var mAdapter: SiswaAdapter
    private var mSiswaList = mutableListOf<Siswa>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_siswa)

        mApi = NetworkConfig.getService().create(ApiEndPoint::class.java)
        mAdapter = SiswaAdapter(this,mSiswaList,{siswa ->  },{siswa ->  deleteSiswa(siswa)})

        val layoutManager = LinearLayoutManager(this)
        siswa_recyclerview.layoutManager = layoutManager
        siswa_recyclerview.adapter = mAdapter

        getSiswa()
    }

    fun getSiswa(){
        mApi.allSiswa().enqueue(object : Callback<SiswaListResponse>{
            override fun onFailure(call: Call<SiswaListResponse>, t: Throwable) {
                var message: String?
                if (t.message != null){
                    if (t.message?.contains("Unable to resolve host")!!){
                        message = "No connection internet"
                    }else{
                        message = t.message
                    }
                }else{
                    message = "Internal server error"
                }
                Toast.makeText(applicationContext,message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<SiswaListResponse>,
                response: Response<SiswaListResponse>
            ) {
                if (response.isSuccessful){
                    val res = response.body()
                    mSiswaList.clear()
                    res?.siswaList?.let { mSiswaList.addAll(it) }
                    mAdapter.notifyDataSetChanged()
                }else{
                    Toast.makeText(applicationContext,response.message(),Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    override fun onResume() {
        super.onResume()
        getSiswa()
    }

    fun gotoEdit(siswa: Siswa){

    }
    
    fun deleteSiswa(siswa: Siswa){
        val request = siswa.id?.toInt()?.let { DeleteSiswaRequest(it) }
        request?.let {
            mApi.deleteSiswa(it).enqueue(object : Callback<com.cahstudio.siswa.datasource.model.response.Response>{
                override fun onFailure(
                    call: Call<com.cahstudio.siswa.datasource.model.response.Response>,
                    t: Throwable
                ) {
                    var message: String?
                    if (t.message != null){
                        if (t.message?.contains("Unable to resolve host")!!){
                            message = "No connection internet"
                        }else{
                            message = t.message
                        }
                    }else{
                        message = "Internal server error"
                    }
                    Toast.makeText(applicationContext,message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<com.cahstudio.siswa.datasource.model.response.Response>,
                    response: Response<com.cahstudio.siswa.datasource.model.response.Response>
                ) {
                    if (response.isSuccessful){
                        getSiswa()
                    }else{
                        Toast.makeText(applicationContext,response.message(),Toast.LENGTH_SHORT).show()
                    }
                }

            })
        }
    }
}