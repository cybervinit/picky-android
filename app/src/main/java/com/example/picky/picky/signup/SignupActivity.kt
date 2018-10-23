package com.example.picky.picky.signup

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.example.picky.picky.PickyApplication
import com.example.picky.picky.R
import com.example.picky.picky.data.models.BaseActivity
import com.example.picky.picky.main.MainActivity
import com.example.picky.picky.signup.di.DaggerSignupActivityComponent
import com.example.picky.picky.signup.di.SignupActivityComponent
import com.example.picky.picky.signup.di.SignupActivityModule
import com.example.picky.picky.signup.interfacing.ISignupPresenter
import com.example.picky.picky.signup.interfacing.ISignupView
import kotlinx.android.synthetic.main.activity_signup.*
import java.util.*
import javax.inject.Inject

class SignupActivity : BaseActivity(), ISignupView {

    val DEBOUNCE_DELAY: Long = 500

    @Inject
    lateinit var signupPresenter: ISignupPresenter.forView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        newUsernameDoneBtn.setOnClickListener {
            signupPresenter.updateUsername(newUsernameEt.text.toString());
        }

        var diComponent: SignupActivityComponent = DaggerSignupActivityComponent
                .builder()
                .appComponent((application as PickyApplication).getAppComponent())
                .signupActivityModule(SignupActivityModule(this))
                .build()
        diComponent.injectSignupActivity(this)

        signupPresenter.checkNewUsername(newUsernameEt.text.toString())

        newUsernameEt.addTextChangedListener(object : TextWatcher {
            private var debounceTimerNewUsernameEt: Timer = Timer()
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                newUsernameDoneBtn.isEnabled = false
                debounceTimerNewUsernameEt.cancel()
                debounceTimerNewUsernameEt = Timer()
                debounceTimerNewUsernameEt.schedule(object : TimerTask() {
                    override fun run() {
                        diComponent.getThreadHandler().post {
                            signupPresenter.checkNewUsername(newUsernameEt.text.toString())
                        }
                    }

                }, DEBOUNCE_DELAY)
            }

        })
    }

    override fun onUsernameChecked(isOk: Boolean, message: String) {
        if (isOk) {
            newUsernameDoneBtn.isEnabled = true
            return
        }
        newUsernameDoneBtn.isEnabled = false
    }

    override fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(0, 0)
        finish()
    }

}
