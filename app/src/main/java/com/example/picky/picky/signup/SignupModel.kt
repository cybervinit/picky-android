package com.example.picky.picky.signup

import android.content.Context
import android.util.Log
import com.example.picky.picky.signup.interfacing.ISignupModel
import com.example.picky.picky.signup.interfacing.ISignupPresenter
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class SignupModel (
        signupPresenter: ISignupPresenter.forModel,
        context: Context
) : ISignupModel {

    private val JSON_MEDIATYPE: MediaType = MediaType.parse("application/json; charset=utf-8")!!

    private var presenter : ISignupPresenter.forModel = signupPresenter
    private var context: Context = context
    private var client : OkHttpClient = OkHttpClient()


    override fun registerUser(username: String) {
        var bodyJson: JSONObject = JSONObject()
        bodyJson.put("username", username)
        var body: RequestBody = RequestBody.create(JSON_MEDIATYPE, bodyJson.toString())
        var registerRequest: Request = Request.Builder()
                .url("https://pickystaging.herokuapp.com/users/registerUser")
                .post(body)
                .build()

        client.newCall(registerRequest).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val jsonString: String = response?.body()?.string() ?: ""
                val respJson: JSONObject = JSONObject(jsonString)
                presenter.onUserRegisterUpdate(respJson.getString("message"))
            }

            override fun onFailure(call: Call?, e: IOException?) {
                presenter.onUserRegisterUpdate("no internet connection") // FIXME: Fix message to being more friendly
            }

        })
    }

    override fun checkUsernameValid(newUsername: String) {
        var checkUsernameValidRequest: Request = Request.Builder()
                .url("https://pickystaging.herokuapp.com/users/isUsernameValid?newUsername=${newUsername}")
                .build()
        client.newCall(checkUsernameValidRequest).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("VINIT", "Request failure: ${e.localizedMessage}")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d("VINIT", "Request succeeded!")
                val jsonString: String = response?.body()?.string() ?: ""
                val respJson: JSONObject = JSONObject(jsonString)
                Log.d("VINIT", respJson.getString("message"))
            }

        })

    }
}