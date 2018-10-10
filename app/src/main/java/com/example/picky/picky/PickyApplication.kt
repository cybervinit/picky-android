package com.example.picky.picky

import android.app.Application
import android.content.Context
import com.example.picky.picky.di.network.DaggerNetworkComponent
import com.example.picky.picky.di.network.NetworkComponent
import okhttp3.OkHttpClient

class PickyApplication : Application() {

    lateinit var networkComponent: NetworkComponent

    override fun onCreate() {
        super.onCreate()
        // TODO: instantiate component for

        networkComponent = DaggerNetworkComponent.builder().build()
        networkComponent.inject(this)

        val client: OkHttpClient = networkComponent.getHttpClient()
    }

    fun getHttpClient(): OkHttpClient {
        return networkComponent.getHttpClient()
    }

    fun app(context: Context): PickyApplication {
        return context.applicationContext as PickyApplication
    }
}
