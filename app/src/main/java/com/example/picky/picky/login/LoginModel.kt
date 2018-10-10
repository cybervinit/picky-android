package com.example.picky.picky.login

import com.example.picky.picky.login.interfacing.ILoginModel
import com.example.picky.picky.login.interfacing.ILoginPresenter

/**
 * Created by vinitsoni on 2018-04-15.
 */
class LoginModel(
        loginPresenter: ILoginPresenter.forModel
) : ILoginModel {

    private var loginPresenter: ILoginPresenter.forModel = loginPresenter

    override fun loginWith(username: String) {
        loginPresenter.loginResult("success", username)
    }
}