package com.example.picky.picky.signup

import android.content.Context
import com.example.picky.picky.signup.interfacing.ISignupModel
import com.example.picky.picky.signup.interfacing.ISignupPresenter
import com.example.picky.picky.signup.interfacing.ISignupView

/**
 * Created by vinitsoni on 2018-05-26.
 */
class SignupPresenter (
        signupView: ISignupView,
        context: Context
) : ISignupPresenter.forView, ISignupPresenter.forModel {

    val OK: String = "ok"
    val SUCCESS_STRING: String = "success"

    private var signupView: ISignupView = signupView
    private var signupModel: ISignupModel = SignupModel(this, context)
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
        signupModel.registerUser(username, passwordHash, phoneNumber)
        /*
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
        */
    }

    override fun onUserRegisterUpdate(message: String) {
        if (message == SUCCESS_STRING) {
            signupView.onRegistrationSuccessful(username, password)
            return
        }
        signupView.onRegistrationFailed(message)
    }

}