package com.example.picky.picky.login

import android.content.Context
import android.util.Log
import com.example.picky.picky.di.scopes.PickyApplicationScope
import com.example.picky.picky.login.dagger.ContextComponent
import com.example.picky.picky.login.interfacing.ILoginModel
import com.example.picky.picky.login.interfacing.ILoginPresenter
import com.example.picky.picky.login.interfacing.ILoginView
import okhttp3.OkHttpClient
import javax.inject.Inject

/**
 *
 */
class LoginPresenter @Inject constructor(
        loginView: ILoginView,
        context: Context,
        httpClient: OkHttpClient // TODO: HERE HERE HERE (i.e. what to do once we have the httpClient?)
) : ILoginPresenter.forModel, ILoginPresenter.forView {

    private var loginView: ILoginView = loginView
    private var loginModel: ILoginModel = LoginModel(this)
    private var context: Context = context

    override fun loginWith(username: String, password: String) {
        this.loginModel.loginWith(username, password)
        // TODO: Hash the password?
    }

    override fun loginResult(status: String, username: String) {
         this.loginView.loginResult(status, username)
        // TODO: function invoked from the model
    }

}