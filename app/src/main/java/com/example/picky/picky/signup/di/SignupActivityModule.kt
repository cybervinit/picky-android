package com.example.picky.picky.signup.di

import android.content.Context
import android.os.Handler
import com.example.picky.picky.di.scopes.ActivityScope
import com.example.picky.picky.helpers.PickyCookieJar
import com.example.picky.picky.signup.SignupActivity
import com.example.picky.picky.signup.SignupPresenter
import com.example.picky.picky.signup.interfacing.ISignupPresenter
import com.example.picky.picky.signup.interfacing.ISignupView
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class SignupActivityModule {

    private val signupActivity: SignupActivity

    constructor(signupActivity: SignupActivity) {
        this.signupActivity = signupActivity
    }

    @Provides
    @ActivityScope
    fun providesSignupActivity(): SignupActivity {
        return signupActivity
    }

    @Provides
    @ActivityScope
    fun provideISignupView(): ISignupView {
        return signupActivity
    }

    @Provides
    @ActivityScope
    fun provideSignupPresenter(signupView: ISignupView, okHttpClient: OkHttpClient, pickyCookieJar: PickyCookieJar, uiThreadHandler: Handler): ISignupPresenter.forView {
        return SignupPresenter(signupView, okHttpClient, pickyCookieJar, uiThreadHandler)
    }
}