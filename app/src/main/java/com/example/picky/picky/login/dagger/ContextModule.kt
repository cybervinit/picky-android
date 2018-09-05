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

    constructor(loginActivity: LoginActivity) {
        this.loginActivity = loginActivity
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

}
