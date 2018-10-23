package com.example.picky.picky.signup

import android.content.Context
import android.os.Handler
import com.example.picky.picky.helpers.PickyCookieJar
import com.example.picky.picky.signup.interfacing.ISignupModel
import com.example.picky.picky.signup.interfacing.ISignupPresenter
import com.example.picky.picky.signup.interfacing.ISignupView
import okhttp3.OkHttpClient
import javax.inject.Inject

/**
 * Created by vinitsoni on 2018-05-26.
 */
class SignupPresenter @Inject constructor(
        signupView: ISignupView,
        okHttpClient: OkHttpClient,
        pickyCookieJar: PickyCookieJar,
        uiThreadHandler: Handler
) : ISignupPresenter.forView, ISignupPresenter.forModel {

    val OK: String = "ok"
    val NOT_OK: String = "not ok"
    val SUCCESS_STRING: String = "success"

    private var signupView: ISignupView = signupView
    private var signupModel: ISignupModel = SignupModel(this, okHttpClient, pickyCookieJar, uiThreadHandler)
    private var username: String = ""

    override fun checkNewUsername(newUsername: String) {
        if (newUsername.length <= 20 && newUsername.length >= 3) {
            this.signupModel.checkUsernameValid(newUsername)
            return
        }
        this.signupView.onUsernameChecked(false, "username must be between 3-20 characters")
    }

    override fun onUsernameValidUpdate(isValidStr: String) {
        if (isValidStr.equals("success")) {
            // TODO: username is valid
            signupView.onUsernameChecked(true, isValidStr)
        } else {
            signupView.onUsernameChecked(false, isValidStr)
        }
    }

    override fun updateUsername(newUsername: String) {
        signupModel.updateUsername(newUsername)
    }

    override fun onUsernameSetUpdate(setMessage: String) {
        if (setMessage.equals("success")) {
            signupView.startMainActivity()
            return
        }
        signupView.showToast(setMessage)
    }

}