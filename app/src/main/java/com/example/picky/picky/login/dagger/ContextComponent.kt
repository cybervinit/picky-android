package com.example.picky.picky.login.dagger

import android.content.Context
import com.example.picky.picky.login.LoginActivity
import com.example.picky.picky.login.LoginPresenter
import dagger.Component
import javax.inject.Inject
import javax.inject.Singleton

@Component (modules = [ ContextModule::class, BinderModule::class ])
interface ContextComponent {

//    fun getContext(): Context
//
//    fun getString(): String

    fun injectLoginActivity(loginActivity: LoginActivity)
}