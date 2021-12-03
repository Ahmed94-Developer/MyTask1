package com.example.myapplication.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myapplication.R
import com.example.myapplication.ui.fragments.MapsFragment
import com.example.myapplication.ui.fragments.PostsFragment
import com.example.myapplication.ui.fragments.SettingsFragment

@Suppress("DEPRECATION")
class PagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {

             when (position) {
                0 -> {
                    return PostsFragment()
                }
                1 -> {
                    return MapsFragment()
                }
                2 ->{
                    return SettingsFragment()
            }
        }
        return PostsFragment()
    }



}