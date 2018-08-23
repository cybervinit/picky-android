package com.example.picky.picky.signup

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.picky.picky.signup.`interface`.ISignupPresenter
import com.example.picky.picky.signup.`interface`.ISignupView

/**
 * Created by vinitsoni on 2018-05-26.
 */
class SignupPresenter (
        signupView: ISignupView,
        context: Context
) : ISignupPresenter.forView, ISignupPresenter.forModel {

    val OK: String = "ok"
    private var signupView: ISignupView = signupView
    private var context: Context = context
    private var username: String = ""
    private var password: String = ""
    private var phoneNumber: String = ""

    override fun checkNewCredentials(newUsername: String, newPhoneNumber: String, newPassword: String) {
        val usernameMsg: String = usernameCheckValid(newUsername)
        val passwordMsg: String = passwordCheckValid(newPassword)
        val phoneNumberMsg: String = phoneNumberCheckValid(newPhoneNumber)
        if (usernameMsg.equals(OK)) {
            if (passwordMsg.equals(OK)) {
                if (phoneNumberMsg.equals(OK)) {
                    this.username = newUsername
                    this.password = newPassword
                    this.phoneNumber = newPhoneNumber
                    signupView.onCredentialsChecked(true, OK)
                    return
                }
                signupView.onCredentialsChecked(false, phoneNumberMsg)
                return
            }
            signupView.onCredentialsChecked(false, passwordMsg)
            return
        }
        signupView.onCredentialsChecked(false, usernameMsg)
        return
    }

    private fun usernameCheckValid(username: String) : String {
        // TODO: check if the username is valid
        return OK
    }

    private fun passwordCheckValid(password: String) : String {
        // TODO: check if the password is valid
        return OK

    }

    private fun phoneNumberCheckValid(phoneNumber: String) : String{
        // TODO: check if the phone number is valid
        return OK
    }

    override fun registerUser() {
        // TODO: hash the password
        val passwordHash: String = password
        // TODO: make the user
        val queue: RequestQueue = Volley.newRequestQueue(context)
        val url: String = "https://pickystaging.herokuapp.com/users/registerUser"
        Log.d("VINIT", "About to initiate request")
        val signupRequest: StringRequest = object : StringRequest(
                Request.Method.POST,
                url,
                Listener<String>{ response ->
                    Log.d("VINIT", response)
                    signupView.onUserCreateAttempt(response)
                    // TODO: store cookies... etc etc
                },
                Response.ErrorListener { err ->

                }

        ){
            override fun getParams() : Map<String, String> {
                var params: HashMap<String, String> = HashMap<String, String>()
                params.put("username", username)
                params.put("passwordHash", passwordHash)
                params.put("phone", phoneNumber)
                return params
            }
        }
        queue.add(signupRequest)

    }
}