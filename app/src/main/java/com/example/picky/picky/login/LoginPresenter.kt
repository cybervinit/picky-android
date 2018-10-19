package com.example.picky.picky.login

import android.content.Context
import com.example.picky.picky.login.interfacing.ILoginModel
import com.example.picky.picky.login.interfacing.ILoginPresenter
import com.example.picky.picky.login.interfacing.ILoginView
import okhttp3.OkHttpClient
import javax.inject.Inject

class LoginPresenter @Inject constructor(
        loginView: ILoginView,
        okHttpClient: OkHttpClient
) : ILoginPresenter.forModel, ILoginPresenter.forView {

    private var loginView: ILoginView = loginView
    private var loginModel: ILoginModel = LoginModel(this, okHttpClient)

    override fun loginWith(username: String) {
        this.loginModel.loginWith(username)
        // TODO: Hash the password?
    }

    override fun loginResult(status: String, username: String) {
         this.loginView.loginResult(status, username)
        // TODO: function invoked from the model
    }

    override fun authWithGoogle(idToken: String) {
        loginModel.authWithGoogle(idToken)
    }

}