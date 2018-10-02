package com.example.picky.picky.login.dagger

import android.content.Context
import com.example.picky.picky.di.scopes.ActivityScope
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
    @ActivityScope
    fun providesLoginActivity(): LoginActivity {
        return loginActivity
    }

    @Provides
    @ActivityScope
    fun providesLoginView(): ILoginView {
        return loginActivity
    }

    @Provides
    @ActivityScope
    fun providesContext(): Context {
        return loginActivity.applicationContext
    }
}
