package com.example.myapplication.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
      Handler(Looper.getMainLooper()).postDelayed({
          val preferences = getSharedPreferences("checkbox", MODE_PRIVATE)
          val checkbox = preferences.getString("remember", "")
          if (checkbox == "true") {
              startActivity(
                  Intent(
                      this, HomeActivity::class.java
                  )
              )
          } else if (checkbox == "") {
              startActivity(Intent(this, LoginActivity::class.java))
          } else if (checkbox == "false") {
              startActivity(Intent(this, LoginActivity::class.java))
          }
          finish()
      }, 3000)

    }
}

