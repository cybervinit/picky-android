package com.example.picky.picky.di.network

import com.example.picky.picky.PickyApplication
import com.example.picky.picky.di.scopes.PickyApplicationScope
import dagger.Component
import okhttp3.OkHttpClient


@PickyApplicationScope
@Component (modules = [NetworkModule::class])
interface NetworkComponent {
    fun inject(pickyApplication: PickyApplication)

//    fun plus(contextModule: ContextModule): ContextComponent

    fun getHttpClient(): OkHttpClient
}
