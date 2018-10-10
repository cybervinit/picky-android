package com.example.picky.picky.signup.interfacing

/**
 * Created by vinitsoni on 2018-05-26.
 */
interface ISignupPresenter {

    interface forView {
        // TODO: add view methods
        fun checkNewUsername(newUsername: String)
        fun checkUsernameValid(newUsername: String)
        fun registerUser()
    }

    interface forModel {
        // TODO: add model methods
        fun onUserRegisterUpdate(message: String)
    }

}