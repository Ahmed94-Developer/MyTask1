package com.example.myapplication.ui.activities

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.R
import com.google.android.material.tabs.TabLayout
import java.util.*


@Suppress("DEPRECATION")
class HomeActivity : AppCompatActivity() {
    var context: Context? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val viewPager: ViewPager = findViewById(R.id.viewPager);
        val tabLyout = findViewById<TabLayout>(R.id.tabLayout)

        viewPager.adapter = com.example.myapplication.adapters.PagerAdapter(supportFragmentManager)
        tabLyout.setupWithViewPager(viewPager)
        tabLyout.getTabAt(0)!!.setText(R.string.posts)
        tabLyout.getTabAt(1)!!.setText(R.string.maps)
        tabLyout.getTabAt(2)!!.setText(R.string.settings)



    }

    override fun onBackPressed() {
        super.onBackPressed()
        try {
            finish()
            LoginActivity.fa.finish()
        }catch (e : Exception){
            e.printStackTrace()
        }

    }




}
