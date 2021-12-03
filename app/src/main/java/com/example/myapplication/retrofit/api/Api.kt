package com.example.myapplication.retrofit.api

import com.example.myapplication.retrofit.data.posts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/posts")
   fun getPostsList(@Query("fbclid") key: String) : Call<List<posts>>

}