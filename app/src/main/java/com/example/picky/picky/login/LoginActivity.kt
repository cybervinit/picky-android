package com.example.picky.picky.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.picky.picky.R
import com.example.picky.picky.login.`interface`.ILoginPresenter
import com.example.picky.picky.login.`interface`.ILoginView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), ILoginView {

    var loginPresenter: ILoginPresenter.forView = LoginPresenter(this)

    override fun loginResult(status: String, username: String) {
        Toast.makeText(this, status + ": " + username, Toast.LENGTH_SHORT).show()
        // TODO: check login status and send to next activity with username
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn.setOnClickListener {
            loginPresenter.loginWith(usernameEt.text.toString(), usernameEt.text.toString())
        }
    }


}
