package com.example.picky.picky.login.`interface`

/**
 * Created by vinitsoni on 2018-04-15.
 */
interface ILoginPresenter {
    interface forView {
        fun loginWith(username: String, password: String)
    }

    interface forModel {
        fun loginResult(status: String, username: String)
    }
}