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
    val NOT_OK: String = "not ok"
    val SUCCESS_STRING: String = "success"

    private var signupView: ISignupView = signupView
    private var signupModel: ISignupModel = SignupModel(this, context)
    private var context: Context = context
    private var username: String = ""

    override fun checkNewUsername(newUsername: String) {
        // TODO("call check new username fct")
    }

    override fun checkUsernameValid(newUsername: String) {
        if (newUsername.length <= 20 && newUsername.length >= 3) {
            this.signupModel.checkUsernameValid(newUsername)
            return
        }
        this.signupView.onUsernameChecked(false, "username must be between 3-20 characters")
    }

    override fun registerUser() {
        signupModel.registerUser(username)
    }

    override fun onUserRegisterUpdate(message: String) {
        if (message == SUCCESS_STRING) {
            signupView.onRegistrationSuccessful(username)
            return
        }
        signupView.onRegistrationFailed(message)
    }

}