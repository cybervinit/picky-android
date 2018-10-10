package com.example.picky.picky.phoneVerification.interfacing

/**
 * Created by vinitsoni on 2018-08-22.
 */
interface IPhoneVerificationModel {
    fun requestPhoneVerification(phoneNumber: String, countryCode: String, via: String)
    fun verifyToken(token: String, countryCode: String, phoneNumber: String)
}