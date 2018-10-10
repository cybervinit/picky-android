package com.example.picky.picky.signup

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import android.widget.Toast
import com.example.picky.picky.R
import com.example.picky.picky.signup.interfacing.ISignupPresenter
import com.example.picky.picky.signup.interfacing.ISignupView
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import java.util.*

class SignupActivity : AppCompatActivity(), ISignupView {

    val DEBOUNCE_DELAY: Long = 1500
    val VERIFY_REQUEST_CODE: Int = 837 // 837 = verify
    val ACTION_VERIFY: String = "com.example.picky.picky.action.VERIFY"
    val PICKY_PERMISSION_READ_PHONE_STATE: Int = 10
    val PHONE_NUMBER_KEY: String = "phone_number"
    val PHONE_NUMBER_IS_VERIFIED_KEY: String = "phone_number_verified"
    private val NEW_USERNAME_KEY = "new_username"

    lateinit var signupPresenter: ISignupPresenter.forView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        signupPresenter = SignupPresenter(this, applicationContext)

        // TODO: Verify username with debouncing

        newUsernameEt.addTextChangedListener(object : TextWatcher {
            private var debounceTimerNewUsernameEt: Timer = Timer()
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                debounceTimerNewUsernameEt.cancel()
                debounceTimerNewUsernameEt = Timer()
                debounceTimerNewUsernameEt.schedule(object : TimerTask() {
                    override fun run() {
                        signupPresenter.checkNewUsername(newUsernameEt.text.toString())
                    }

                }, DEBOUNCE_DELAY)
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == VERIFY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val isVerified: Boolean = data.getBooleanExtra(PHONE_NUMBER_IS_VERIFIED_KEY, false)
                Toast.makeText(this, "Did user verify: " + isVerified.toString(), Toast.LENGTH_SHORT).show() // FIXME: remove Toast?
                if (isVerified) {
                    signupPresenter.registerUser()
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "couldn't verify", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onUsernameChecked(isOk: Boolean, message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onUserCreateAttempt(responseMessage: String) {
        Toast.makeText(this, responseMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onRegistrationFailed(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    override fun onRegistrationSuccessful(username: String) {
        val resultIntent: Intent = Intent()
        resultIntent.putExtra(NEW_USERNAME_KEY, username)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        super.onBackPressed()
        finish()
    }
}
