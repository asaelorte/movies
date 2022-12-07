package com.example.movies.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.movies.R
import com.example.movies.ui.main.MainActivity

class SplasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread.sleep(3000)
        screenSplash.setKeepOnScreenCondition{true}
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}