package com.example.picky.picky.signup.interfacing

/**
 * Created by vinitsoni on 2018-05-26.
 */
interface ISignupView {
    fun onUsernameChecked(isOk: Boolean, message: String)

    // FIXME: Below method to be deleted
    fun onUserCreateAttempt(responseMessage: String)

    fun onRegistrationSuccessful(username: String)
    fun onRegistrationFailed(message: String)

}