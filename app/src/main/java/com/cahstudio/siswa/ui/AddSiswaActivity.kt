package com.cahstudio.siswa.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cahstudio.siswa.R
import com.cahstudio.siswa.datasource.model.request.AddSiswaRequest
import com.cahstudio.siswa.datasource.model.response.Response
import com.cahstudio.siswa.datasource.network.ApiEndPoint
import com.cahstudio.siswa.datasource.network.NetworkConfig
import kotlinx.android.synthetic.main.activity_add_siswa.*
import retrofit2.Call
import retrofit2.Callback

class AddSiswaActivity : AppCompatActivity() {
    private lateinit var mApi: ApiEndPoint

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_siswa)

        mApi = NetworkConfig.getService().create(ApiEndPoint::class.java)

        addsiswa_btnAdd.setOnClickListener {
            addSiswa()
        }
    }

    fun addSiswa(){
        val name = addsiswa_etName.text.toString()
        val gender = addsiswa_etGender.text.toString()

        val request = AddSiswaRequest(name,gender)
        mApi.addSiswa(request).enqueue(object : Callback<Response>{
            override fun onFailure(call: Call<Response>, t: Throwable) {
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

            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                Toast.makeText(applicationContext,response.body()?.message, Toast.LENGTH_SHORT).show()
                if (response.isSuccessful){
                    finish()
                }else{
                    Toast.makeText(applicationContext,response.message(),Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
}