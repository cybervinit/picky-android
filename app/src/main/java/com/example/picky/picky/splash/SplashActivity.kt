package com.example.picky.picky.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.picky.picky.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: Fix the logo and setup proper logos
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
