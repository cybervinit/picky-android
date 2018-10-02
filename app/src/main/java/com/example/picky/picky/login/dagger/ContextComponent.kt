package com.example.picky.picky.login.dagger

import android.content.Context
import com.example.picky.picky.di.NetworkComponent
import com.example.picky.picky.di.scopes.ActivityScope
import com.example.picky.picky.login.LoginActivity
import com.example.picky.picky.login.LoginPresenter
import dagger.Component
import dagger.Subcomponent
import javax.inject.Inject
import javax.inject.Singleton


@ActivityScope
@Component (dependencies = [ NetworkComponent::class ], modules = [ ContextModule::class, BinderModule::class ])
interface ContextComponent {

//    fun getContext(): Context
//
//    fun getString(): String

    fun injectLoginActivity(loginActivity: LoginActivity)
}