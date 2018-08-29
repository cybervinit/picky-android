package com.example.picky.picky.signup

import android.content.Context
import android.util.Log
import com.example.picky.picky.signup.`interface`.ISignupModel
import com.example.picky.picky.signup.`interface`.ISignupPresenter
import com.google.gson.JsonObject
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


    override fun registerUser(username: String, passwordHash: String, phoneNumber: String) {
        var bodyJson: JSONObject = JSONObject()
        bodyJson.put("username", username)
        bodyJson.put("phone", phoneNumber)
        bodyJson.put("passwordHash", passwordHash)
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
}