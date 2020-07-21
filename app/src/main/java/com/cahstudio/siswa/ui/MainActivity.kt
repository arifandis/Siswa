package com.cahstudio.siswa.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.enums.Align
import com.anychart.enums.LegendLayout
import com.cahstudio.siswa.R
import com.cahstudio.siswa.datasource.model.response.ChartRespose
import com.cahstudio.siswa.datasource.network.ApiEndPoint
import com.cahstudio.siswa.datasource.network.NetworkConfig
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var mApi: ApiEndPoint

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mApi = NetworkConfig.getService().create(ApiEndPoint::class.java)
        getChartData()

        main_btnSiswa.setOnClickListener {
            startActivity(Intent(this, SiswaActivity::class.java))
        }
    }

    fun getChartData(){
        mApi.chart().enqueue(object : Callback<ChartRespose>{
            override fun onFailure(call: Call<ChartRespose>, t: Throwable) {
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
                Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ChartRespose>, response: Response<ChartRespose>) {
                if (response.isSuccessful){
                    val res = response.body()
                    res?.men?.let { showChart(it,res.women) }
                }else{
                    showChart(0,0)
                    Toast.makeText(applicationContext,response.message(),Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun showChart(men: Int, women: Int){
        val pie = AnyChart.pie()
        val data: MutableList<DataEntry> = ArrayList()
        data.add(ValueDataEntry("Laki-laki", men))
        data.add(ValueDataEntry("Perempuan", women))

        pie.data(data)

        pie.title("Data Jurusan SMK Maju Mundur")

        pie.labels().position("outside")

        pie.legend().title().enabled(true)
        pie.legend().title()
            .text("Jumlah berdasarkan jenis kelamin")
            .padding(0.0, 0.0, 10.0, 0.0)

        pie.legend()
            .position("center-bottom")
            .itemsLayout(LegendLayout.HORIZONTAL)
            .align(Align.CENTER)

        main_acvJurusan.setChart(pie)
    }

    override fun onResume() {
        super.onResume()
        getChartData()
    }
}