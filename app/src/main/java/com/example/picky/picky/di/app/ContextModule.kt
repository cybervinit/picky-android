package com.example.picky.picky.di.app

import android.content.Context
import com.example.picky.picky.PickyApplication
import com.example.picky.picky.di.scopes.ActivityScope
import com.example.picky.picky.di.scopes.PickyApplicationScope
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
    private lateinit var pickyApplication: PickyApplication

//    constructor(loginActivity: LoginActivity) {
//        this.loginActivity = loginActivity
//    }

    constructor(pickyApplication: PickyApplication) {
        this.pickyApplication = pickyApplication
    }


//    @Provides
//    @ActivityScope
//    fun providesLoginActivity(): LoginActivity {
//        return loginActivity
//    }
//
//    @Provides
//    @ActivityScope
//    fun providesLoginView(): ILoginView {
//        return loginActivity
//    }

    @Provides
    @PickyApplicationScope
    fun providesContext(): Context {
        return pickyApplication.applicationContext
    }
}
