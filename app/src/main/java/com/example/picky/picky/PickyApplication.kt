package com.example.picky.picky

import android.app.Application
import android.content.Context
import com.example.picky.picky.di.app.AppComponent
import com.example.picky.picky.di.app.DaggerAppComponent
import okhttp3.OkHttpClient

class PickyApplication : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        // TODO: instantiate component for

        appComponent = DaggerAppComponent
                .builder()
                .build()



        val client: OkHttpClient = appComponent.exposeOkHttpClient()
    }

    fun getHttpClient(): OkHttpClient {
        return appComponent.exposeOkHttpClient()
    }

    fun app(context: Context): PickyApplication {
        return context.applicationContext as PickyApplication
    }
}
