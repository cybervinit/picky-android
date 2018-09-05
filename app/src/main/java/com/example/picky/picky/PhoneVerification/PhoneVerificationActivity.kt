package com.example.picky.picky.PhoneVerification

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.picky.picky.PhoneVerification.interfacing.IPhoneVerificationPresenter
import com.example.picky.picky.PhoneVerification.interfacing.IPhoneVerificationView
import com.example.picky.picky.R
import kotlinx.android.synthetic.main.activity_phone_verification.*

class PhoneVerificationActivity : AppCompatActivity(), IPhoneVerificationView {

    val PHONE_NUMBER_KEY: String = "phone_number"
    val PHONE_NUMBER_IS_VERIFIED_KEY: String = "phone_number_verified"

    private var pvPresenter: IPhoneVerificationPresenter.forView = PhoneVerificationPresenter(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_verification)

        val phoneNumber: String = intent.getStringExtra(PHONE_NUMBER_KEY)

        phoneNumberTv.setText(phoneNumber)

        // TODO: 1. Send request to get a verification token from Twilio
        pvPresenter.requestPhoneVerification(phoneNumber, "1", "sms") // FIXME can change to other options later...

        // TODO: 2. Alow user to enter the token

        verifyBtn.setOnClickListener {
            // TODO: 3. Verify with Twilio (backend) if the token is the same
            pvPresenter.verifyToken(verificationCodeEt.text.toString(), "1", phoneNumber)
        }
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        super.onBackPressed()
        finish()
    }

    override fun onPhoneVerifyStatusUpdate(isVerified: Boolean) {
        var result: Intent = Intent()

        result.putExtra(PHONE_NUMBER_IS_VERIFIED_KEY, isVerified) // FIXME: Maybe it shouldn't just be a boolean "true"
        setResult(Activity.RESULT_OK, result)
        finish()
    }
}
