package com.example.picky.picky.login.di

import android.content.Context
import android.os.Handler
import com.example.picky.picky.di.scopes.ActivityScope
import com.example.picky.picky.helpers.PickyCookieJar
import com.example.picky.picky.login.LoginActivity
import com.example.picky.picky.login.LoginModel
import com.example.picky.picky.login.LoginPresenter
import com.example.picky.picky.login.interfacing.ILoginModel
import com.example.picky.picky.login.interfacing.ILoginPresenter
import com.example.picky.picky.login.interfacing.ILoginView
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Named

@Module
class LoginActivityModule {

    private lateinit var loginActivity: LoginActivity;

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
    fun providesLoginPresenter(loginView: ILoginView, okHttpClient: OkHttpClient, pickyCookieJar: PickyCookieJar, handler: Handler): ILoginPresenter.forView {
        return LoginPresenter(loginView, okHttpClient, pickyCookieJar, handler) // TODO: remove SetCookieCache
    }

    @Provides
    @ActivityScope
    fun providesLoginModel(loginPresenter: LoginPresenter, okHttpClient: OkHttpClient, pickyCookieJar: PickyCookieJar, handler: Handler): ILoginModel {
        return LoginModel(loginPresenter, okHttpClient, pickyCookieJar, handler)
    }



}