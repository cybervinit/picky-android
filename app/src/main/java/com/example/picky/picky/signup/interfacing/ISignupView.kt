package com.example.picky.picky.signup.interfacing

/**
 * Created by vinitsoni on 2018-05-26.
 */
interface ISignupView {
    fun onUsernameChecked(isOk: Boolean, message: String)
    fun startMainActivity()
    fun showToast(toastMessage: String)
}