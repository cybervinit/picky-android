package com.example.picky.picky.di

import com.example.picky.picky.di.scopes.PickyApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

// FIXME: Change @Singleton scopes to your own made scopes

@Module
class NetworkModule {

    @Provides
    @PickyApplicationScope
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }
}