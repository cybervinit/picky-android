package com.example.picky.picky.PhoneVerification.`interface`

/**
 * Created by vinitsoni on 2018-08-22.
 */
interface IPhoneVerificationPresenter {
    interface forView {
        fun requestPhoneVerification(phoneNumber: String, countryCode: String, via: String)
        fun verifyToken(token: String, countryCode: String, phoneNumber: String)
    }

    interface forModel {
        // TODO: methods for the model
        fun onPhoneVerifyStatusUpdated(isVerified: Boolean)
    }
}