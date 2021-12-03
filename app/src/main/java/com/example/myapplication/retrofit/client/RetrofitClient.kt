package com.example.myapplication.retrofit.client

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

open class RetrofitClient {

companion object{
    private var instance: Retrofit? = null
    fun getClient(baseUrl: String?): Retrofit? {
        if (instance == null) {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            instance = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return instance
    }
}



}