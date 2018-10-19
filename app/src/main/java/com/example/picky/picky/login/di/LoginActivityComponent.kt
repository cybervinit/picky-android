package com.example.picky.picky.login.di


import android.content.Context
import com.example.picky.picky.di.app.AppComponent
import com.example.picky.picky.di.scopes.ActivityScope
import com.example.picky.picky.login.LoginActivity
import com.example.picky.picky.login.interfacing.ILoginView
import dagger.Component
import okhttp3.OkHttpClient


@ActivityScope
@Component (modules = [ LoginActivityModule::class, LoginPresenterBindModule::class ], dependencies = [ AppComponent::class ])
interface LoginActivityComponent {
    fun injectLoginActivity(loginActivity: LoginActivity)
}