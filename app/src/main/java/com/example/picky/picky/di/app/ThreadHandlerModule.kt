package com.example.picky.picky.di.app

import android.os.Handler
import android.os.Looper
import com.example.picky.picky.di.scopes.PickyApplicationScope
import dagger.Module
import dagger.Provides


@Module
class ThreadHandlerModule {

    @Provides
    @PickyApplicationScope
    fun providesHandler(): Handler {
        return Handler(Looper.getMainLooper())
    }
}