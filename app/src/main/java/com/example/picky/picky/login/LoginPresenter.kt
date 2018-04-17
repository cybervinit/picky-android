package com.example.picky.picky.login

import com.example.picky.picky.login.`interface`.ILoginModel
import com.example.picky.picky.login.`interface`.ILoginPresenter
import com.example.picky.picky.login.`interface`.ILoginView

/**
 *
 */
class LoginPresenter(
        loginView: ILoginView
) : ILoginPresenter.forModel, ILoginPresenter.forView {

    private var loginView: ILoginView = loginView
    private var loginModel: ILoginModel = LoginModel(this)

    override fun loginWith(username: String, password: String) {
        this.loginModel.loginWith(username, password)
        // TODO: Hash the password?
    }

    override fun loginResult(status: String, username: String) {
        this.loginView.loginResult(status, username)
        // TODO: function invoked from the model
    }

}