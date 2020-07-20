package com.cahstudio.siswa.datasource.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkConfig {
    companion object{

        val BASE_URL = "http://192.168.0.3:8888/api-siswa/index.php/"

        fun getService(): Retrofit {
            val client = OkHttpClient.Builder()
            client.connectTimeout(15, TimeUnit.SECONDS)
            client.readTimeout(15, TimeUnit.SECONDS)
            client.writeTimeout(15, TimeUnit.SECONDS)

            val gson = GsonBuilder()
                .setLenient()
                .create()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client.build())
                .build()
        }
    }
}