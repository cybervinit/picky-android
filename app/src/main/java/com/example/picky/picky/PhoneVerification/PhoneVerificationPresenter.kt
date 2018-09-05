package com.example.picky.picky.PhoneVerification

import android.content.Context
import com.example.picky.picky.PhoneVerification.interfacing.IPhoneVerificationModel
import com.example.picky.picky.PhoneVerification.interfacing.IPhoneVerificationPresenter
import com.example.picky.picky.PhoneVerification.interfacing.IPhoneVerificationView

class PhoneVerificationPresenter(
        phoneVerificationView: IPhoneVerificationView,
        c: Context
): IPhoneVerificationPresenter.forView, IPhoneVerificationPresenter.forModel {


    override fun onPhoneVerifyStatusUpdated(isVerified: Boolean) {
        phoneVerificationView.onPhoneVerifyStatusUpdate(isVerified)
    }

    private var phoneVerificationView: IPhoneVerificationView =  phoneVerificationView
    private var phoneVerificationModel: IPhoneVerificationModel = PhoneVerificationModel(this, c)

    override fun requestPhoneVerification(phoneNumber: String, countryCode: String, via: String) {
        phoneVerificationModel.requestPhoneVerification(phoneNumber, countryCode, via)
    }

    override fun verifyToken(token: String, countryCode: String, phoneNumber: String) {
        phoneVerificationModel.verifyToken(token, countryCode, phoneNumber)
    }


}