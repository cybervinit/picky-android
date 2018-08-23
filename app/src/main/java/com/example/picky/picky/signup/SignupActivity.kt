package com.example.picky.picky.signup

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.picky.picky.R
import com.example.picky.picky.signup.`interface`.ISignupPresenter
import com.example.picky.picky.signup.`interface`.ISignupView
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity(), ISignupView {

    val VERIFY_REQUEST_CODE: Int = 837 // 837 = verify
    val ACTION_VERIFY: String = "com.example.picky.picky.action.VERIFY"
    val PHONE_NUMBER_KEY: String = "phone_number"
    val PHONE_NUMBER_IS_VERIFIED_KEY: String = "phone_number_verified"

    var signupPresenter: ISignupPresenter.forView = SignupPresenter(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        sendVerificationCodeBtn.setOnClickListener {
            signupPresenter.checkNewCredentials(
                    newUsernameEt.text.toString(),
                    newPhoneEt.text.toString(),
                    newPasswordEt.text.toString())

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == VERIFY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, data?.getBooleanExtra(PHONE_NUMBER_IS_VERIFIED_KEY, false).toString(), Toast.LENGTH_SHORT).show()
                // TODO: check if the phone number is verified...
                // TODO: if verified, attempt user signup and set a boolean that the phone number is
                // verified so don't have to keep verifying if they can't automatically signup after
                // phone verify
                signupPresenter.registerUser()
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "It's didn't work :(", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCredentialsChecked(isOk: Boolean, message: String) {
        if (isOk) {
            val verifyIntent: Intent = Intent(ACTION_VERIFY) // FIXME: can URI strings be empty?
            verifyIntent.putExtra(PHONE_NUMBER_KEY, newPhoneEt.text.toString())
            startActivityForResult(verifyIntent, VERIFY_REQUEST_CODE)
            return
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onUserCreateAttempt(responseMessage: String) {
        Toast.makeText(this, responseMessage, Toast.LENGTH_SHORT).show()
    }
}
