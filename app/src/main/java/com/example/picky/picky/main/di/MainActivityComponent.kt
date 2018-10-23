package com.example.picky.picky.main.di

import com.example.picky.picky.di.app.AppComponent
import com.example.picky.picky.di.scopes.ActivityScope
import com.example.picky.picky.helpers.PickyCookieJar
import com.example.picky.picky.main.MainActivity
import dagger.Component

@Component ( modules = [ MainActivityModule::class ], dependencies = [ AppComponent::class ])
@ActivityScope
interface MainActivityComponent {
    fun injectMainActivity(mainActivity: MainActivity)

    fun getCookieJar(): PickyCookieJar
}