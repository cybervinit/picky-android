package com.example.picky.picky.di.app

import android.content.Context
import com.example.picky.picky.PickyApplication
import com.example.picky.picky.di.scopes.PickyApplicationScope
import com.example.picky.picky.login.LoginActivity
import com.example.picky.picky.login.di.LoginPresenterBindModule
import dagger.Component
import okhttp3.OkHttpClient

@PickyApplicationScope
@Component(modules = [ ContextModule::class, NetworkModule::class ])
interface AppComponent {
    fun inject(pickyApplication: PickyApplication);

    fun exposeContext(): Context
    fun exposeOkHttpClient(): OkHttpClient
}