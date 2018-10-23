package com.example.picky.picky.main

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.example.picky.picky.PickyApplication
import com.example.picky.picky.R
import com.example.picky.picky.helpers.CookieWrapper
import com.example.picky.picky.main.di.DaggerMainActivityComponent
import com.example.picky.picky.main.di.MainActivityComponent
import com.example.picky.picky.main.di.MainActivityModule

import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Cookie
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val diComponent: MainActivityComponent = DaggerMainActivityComponent.builder()
                .appComponent((application as PickyApplication).getAppComponent())
                .build()
        diComponent.injectMainActivity(this)

        temp.text = JSONObject(CookieWrapper(diComponent.getCookieJar().sessionCookie).decodedValue).toString()
    }

}
