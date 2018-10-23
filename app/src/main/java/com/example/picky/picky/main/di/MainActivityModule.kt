package com.example.picky.picky.main.di

import com.example.picky.picky.main.MainActivity
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    private lateinit var mainActivity: MainActivity

    constructor(mainActivity: MainActivity) {
        this.mainActivity = mainActivity
    }
}