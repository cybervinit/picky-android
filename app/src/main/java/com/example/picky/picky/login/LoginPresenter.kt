package com.example.picky.picky.login

import android.content.Context
import android.os.Handler
import android.util.Log
import com.example.picky.picky.helpers.PickyCookieJar
import com.example.picky.picky.login.interfacing.ILoginModel
import com.example.picky.picky.login.interfacing.ILoginPresenter
import com.example.picky.picky.login.interfacing.ILoginView
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import okhttp3.OkHttpClient
import javax.inject.Inject

class LoginPresenter @Inject constructor(
        loginView: ILoginView,
        okHttpClient: OkHttpClient,
        pickyCookieJar: PickyCookieJar,
        handler: Handler
) : ILoginPresenter.forModel, ILoginPresenter.forView {

    private var loginView: ILoginView = loginView
    public var loginModel: ILoginModel = LoginModel(this, okHttpClient, pickyCookieJar, handler)

    override fun loginResult(status: String) {
         this.loginView.loginResult(status)
    }

    override fun authWithGoogle(idToken: String) {
        loginModel.authWithGoogle(idToken)
    }

}