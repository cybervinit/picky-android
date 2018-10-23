package com.example.picky.picky.signup.di

import android.os.Handler
import com.example.picky.picky.di.app.AppComponent
import com.example.picky.picky.di.scopes.ActivityScope
import com.example.picky.picky.signup.SignupActivity
import dagger.Component


@ActivityScope
@Component (modules = [ SignupActivityModule::class ], dependencies = [ AppComponent::class ])
interface SignupActivityComponent {
    fun injectSignupActivity(signupActivity: SignupActivity)
    fun getThreadHandler(): Handler
}