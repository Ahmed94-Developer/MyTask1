package com.example.myapplication.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.retrofit.service.ApiService
import com.example.myapplication.retrofit.data.posts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class PostsRepository(private val application: Application) {
    private var postsLiveData: MutableLiveData<List<posts>> = MutableLiveData()
    var postsApi = ApiService.getPostsApiService()

 fun getPosts(){
        postsApi!!.getPostsList("IwAR3DqwEKFNJShHZ_4XT9BUcm0g7U8Co5sAyhk_HAqjXFiJbQn3qHryBKlA4").enqueue(object : Callback<List<posts>> {
            override fun onResponse(call: Call<List<posts>>, response: Response<List<posts>>) {
                postsLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<List<posts>>, t: Throwable) {
                Toast.makeText(application.applicationContext,"Failed",Toast.LENGTH_LONG).show()
            }

        })
    }
   open fun postsLiveData () : LiveData<List<posts>>{
        return postsLiveData
    }

}