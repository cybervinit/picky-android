package com.example.picky.picky.signup.interfacing

/**
 * Created by vinitsoni on 2018-05-26.
 */
interface ISignupModel {
    fun checkUsernameValid(newUsername: String)
    fun updateUsername(newUsername: String)
}