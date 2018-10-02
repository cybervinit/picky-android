package com.example.picky.picky.di

import com.example.picky.picky.PickyApplication
import com.example.picky.picky.di.scopes.PickyApplicationScope
import com.example.picky.picky.login.dagger.ContextComponent
import com.example.picky.picky.login.dagger.ContextModule
import dagger.Component
import okhttp3.OkHttpClient
import javax.inject.Singleton


@PickyApplicationScope
@Component (modules = [NetworkModule::class])
interface NetworkComponent {
    fun inject(pickyApplication: PickyApplication)

//    fun plus(contextModule: ContextModule): ContextComponent

    fun getHttpClient(): OkHttpClient
}
