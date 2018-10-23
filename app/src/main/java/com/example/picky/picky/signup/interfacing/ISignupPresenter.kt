package com.example.picky.picky.signup.interfacing

/**
 * Created by vinitsoni on 2018-05-26.
 */
interface ISignupPresenter {

    interface forView {
        fun checkNewUsername(newUsername: String)
        fun updateUsername(toString: String)
    }

    interface forModel {
        fun onUsernameValidUpdate(isValidStr: String)
        fun onUsernameSetUpdate(string: String)
    }

}