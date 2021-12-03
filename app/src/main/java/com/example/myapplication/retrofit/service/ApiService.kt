package com.example.myapplication.retrofit.service

import com.example.myapplication.retrofit.client.RetrofitClient
import com.example.myapplication.retrofit.api.Api

open class ApiService {
    companion object{
       fun getPostsApiService(): Api? {
            return RetrofitClient.getClient("https://jsonplaceholder.typicode.com")!!.create(Api::class.java)
        }
    }

}