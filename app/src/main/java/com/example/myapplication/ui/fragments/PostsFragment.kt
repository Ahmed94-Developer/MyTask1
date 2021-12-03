package com.example.myapplication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

import com.example.myapplication.R
import com.example.myapplication.adapters.PostsAdapter
import com.example.myapplication.viewmodel.PostsViewModel
import kotlinx.android.synthetic.main.activity_home.view.*
import kotlinx.android.synthetic.main.fragment_posts.view.*


class PostsFragment : Fragment() {
    private lateinit var postsViewModel: PostsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_posts, container, false)
        postsViewModel = ViewModelProvider(this
            ,ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))
            .get(PostsViewModel::class.java)

        val linearLayoutManager : LinearLayoutManager = LinearLayoutManager(activity)
        postsViewModel.getPostsList()
        postsViewModel.getPostsLiveData().observe(viewLifecycleOwner, Observer {
            view.posts_recycler.layoutManager = linearLayoutManager
            view.posts_recycler.adapter = PostsAdapter(it)
        })


        return view
    }
}
