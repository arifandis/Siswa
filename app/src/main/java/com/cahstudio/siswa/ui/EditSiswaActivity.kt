package com.cahstudio.siswa.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cahstudio.siswa.R
import com.cahstudio.siswa.datasource.model.Siswa
import com.cahstudio.siswa.datasource.model.request.UpdateSiswaRequest
import com.cahstudio.siswa.datasource.model.response.Response
import com.cahstudio.siswa.datasource.network.ApiEndPoint
import com.cahstudio.siswa.datasource.network.NetworkConfig
import kotlinx.android.synthetic.main.activity_edit_siswa.*
import retrofit2.Call
import retrofit2.Callback

class EditSiswaActivity : AppCompatActivity() {
    private lateinit var mSiswa: Siswa
    private lateinit var mApi: ApiEndPoint

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_siswa)

        mApi = NetworkConfig.getService().create(ApiEndPoint::class.java)
        mSiswa = intent.getParcelableExtra("siswa")

        editsiswa_etName.setText(mSiswa.name)
        editsiswa_etGender.setText(mSiswa.gender)

        editsiswa_btnSave.setOnClickListener {
            editSiswa()
        }
    }

    fun editSiswa(){
        val name = editsiswa_etName.text.toString()
        val gender = editsiswa_etGender.text.toString()

        val request = mSiswa.id?.toInt()?.let { UpdateSiswaRequest(it,name,gender) }
        request?.let {
            mApi.updateSiswa(it).enqueue(object : Callback<Response>{
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
                    if (response.isSuccessful){
                        Toast.makeText(applicationContext,response.body()?.message, Toast.LENGTH_SHORT).show()
                        finish()
                    }else{
                        Toast.makeText(applicationContext,response.message(), Toast.LENGTH_SHORT).show()
                    }
                }

            })
        }
    }
}