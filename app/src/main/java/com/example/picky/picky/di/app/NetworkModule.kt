package com.example.picky.picky.di.app

import android.content.Context
import com.example.picky.picky.di.scopes.PickyApplicationScope
import com.franmontiel.persistentcookiejar.ClearableCookieJar
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.CookieCache
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Singleton

// FIXME: Change @Singleton scopes to your own made scopes

@Module
class NetworkModule {

    @Provides
    @PickyApplicationScope
    fun providesOkHttpClient(/*context: Context*/): OkHttpClient {
//        var cookieJar: ClearableCookieJar = PersistentCookieJar(
//                SetCookieCache(), SharedPrefsCookiePersistor(context))
        return OkHttpClient()// .Builder()
//                .cookieJar(cookieJar)
//                .build()
    }
}