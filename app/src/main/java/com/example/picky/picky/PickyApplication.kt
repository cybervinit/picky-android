package com.example.picky.picky

import android.app.Application
import android.widget.Toast
import okhttp3.OkHttpClient

class PickyApplication : Application() {

    lateinit var networkComponent: NetworkComponent

    override fun onCreate() {
        super.onCreate()
        // TODO: instantiate component for
        networkComponent = DaggerNetworkComponent.builder().build()
        networkComponent.inject(this)
    }

    fun getHttpClient(): OkHttpClient {
        return networkComponent.getHttpClient()
    }
}
