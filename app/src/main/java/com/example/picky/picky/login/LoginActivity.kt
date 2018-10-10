package com.example.picky.picky.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.widget.Toast
import com.example.picky.picky.PickyApplication
import com.example.picky.picky.R
import com.example.picky.picky.di.context.ContextComponent
import com.example.picky.picky.di.context.ContextModule
import com.example.picky.picky.di.context.DaggerContextComponent
import com.example.picky.picky.login.interfacing.ILoginPresenter
import com.example.picky.picky.login.interfacing.ILoginView
import com.example.picky.picky.signup.SignupActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), ILoginView {

    private val NEW_USER_REQUEST_CODE: Int = 1 // 1: Request code to return username and password from the SignupActivity
    private val ACTION_NEW_USER = "com.example.picky.picky.action.NEW_USER"
    private val NEW_USERNAME_KEY = "new_username"

    @Inject lateinit var loginPresenter: ILoginPresenter.forView

    private lateinit var context: Context





    override fun loginResult(status: String, username: String) {
        Toast.makeText(this, status + ": " + username, Toast.LENGTH_SHORT).show()
        // TODO: check login status and send to next activity with username
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn.setOnClickListener { login() }

        var contextComponent: ContextComponent = DaggerContextComponent
                .builder()
                .networkComponent((application as PickyApplication).app(this).networkComponent)
                .contextModule(ContextModule(this))
                .build()
        contextComponent.injectLoginActivity(this)


        context = this

        val displayMetrics: DisplayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)

        signUpBtn.setOnClickListener {
            startActivityForResult(Intent(this, SignupActivity::class.java), NEW_USER_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_USER_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
//                usernameEt.setText(data?.getStringExtra(NEW_USERNAME_KEY)) // TODO: Remove line
                Toast.makeText(applicationContext, "Time to login with the creds!", Toast.LENGTH_SHORT).show() // FIXME: remove Toast?
                // TODO: login()
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(applicationContext, "Registration cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun login() {
        // FIXME: Sign in with OAuth
    }

    companion object {
        fun getContext(loginActivity: LoginActivity): Context {
            return loginActivity
        }
    }

}
