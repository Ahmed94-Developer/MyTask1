package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.repository.PostsRepository
import com.example.myapplication.retrofit.data.posts

open class PostsViewModel(application: Application) : AndroidViewModel(application) {
    private  var postsRepository: PostsRepository = PostsRepository(application)
    private var postsLiveData : LiveData<List<posts>> = postsRepository.postsLiveData()

    open fun getPostsList(){
        postsRepository.getPosts()
    }
    open fun getPostsLiveData() : LiveData<List<posts>>{
        return postsLiveData
    }

}