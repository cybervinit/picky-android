package com.example.picky.picky.signup.interfacing

/**
 * Created by vinitsoni on 2018-05-26.
 */
interface ISignupModel {

    fun registerUser(username: String, passwordHash: String, phoneNumber: String)
}