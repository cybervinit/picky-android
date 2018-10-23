package com.example.picky.picky.di.app

import android.content.Context
import android.os.Handler
import com.example.picky.picky.PickyApplication
import com.example.picky.picky.di.scopes.PickyApplicationScope
import com.example.picky.picky.helpers.PickyCookieJar
import com.example.picky.picky.login.LoginActivity
import com.example.picky.picky.login.di.LoginPresenterBindModule
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import dagger.Component
import okhttp3.OkHttpClient

@PickyApplicationScope
@Component(modules = [ ContextModule::class, NetworkModule::class, ThreadHandlerModule::class ])
interface AppComponent {
    fun inject(pickyApplication: PickyApplication)

    fun exposeContext(): Context
    fun exposeOkHttpClient(): OkHttpClient
    fun exposePickyCookieJar(): PickyCookieJar
    fun exposeThreadHandler(): Handler
}