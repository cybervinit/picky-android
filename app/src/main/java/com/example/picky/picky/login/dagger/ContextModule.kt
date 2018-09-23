package com.example.picky.picky.login.dagger

import android.content.Context
import com.example.picky.picky.login.LoginActivity
import com.example.picky.picky.login.interfacing.ILoginView
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

@Module
class ContextModule {

    private lateinit var loginActivity: LoginActivity
    private lateinit var httpClient: OkHttpClient

    constructor(loginActivity: LoginActivity, httpClient: OkHttpClient) {
        this.loginActivity = loginActivity
        this.httpClient = httpClient
    }


    @Provides
    fun providesLoginActivity(): LoginActivity {
        return loginActivity
    }

    @Provides
    fun providesLoginView(): ILoginView {
        return loginActivity
    }

    @Provides
    fun providesContext(): Context {
        return loginActivity.applicationContext
    }

    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        return httpClient
    }


}
