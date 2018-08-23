package com.example.picky.picky.signup.`interface`

/**
 * Created by vinitsoni on 2018-05-26.
 */
interface ISignupView {
    fun onCredentialsChecked(isOk: Boolean, message: String)

    // FIXME: Below method to be deleted
    fun onUserCreateAttempt(responseMessage: String)

}