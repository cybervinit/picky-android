package com.example.picky.picky.login.interfacing

/**
 * Created by vinitsoni on 2018-04-15.
 */
interface ILoginPresenter {
    interface forView {
        fun authWithGoogle(idToken: String)
    }

    interface forModel {
        fun loginResult(status: String)
    }
}