package com.example.picky.picky.di.context

import com.example.picky.picky.di.network.NetworkComponent
import com.example.picky.picky.di.scopes.ActivityScope
import com.example.picky.picky.login.LoginActivity
import dagger.Component


@ActivityScope
@Component (dependencies = [ NetworkComponent::class ], modules = [ ContextModule::class, ContextBindModule::class ])
interface ContextComponent {

//    fun getContext(): Context
//
//    fun getString(): String

    fun injectLoginActivity(loginActivity: LoginActivity)
}