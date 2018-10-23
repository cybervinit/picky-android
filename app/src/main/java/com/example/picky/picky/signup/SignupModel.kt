package com.example.picky.picky.signup

import android.content.Context
import android.os.Handler
import android.util.Log
import com.example.picky.picky.helpers.PickyCookieJar
import com.example.picky.picky.signup.interfacing.ISignupModel
import com.example.picky.picky.signup.interfacing.ISignupPresenter
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class SignupModel (
        signupPresenter: ISignupPresenter.forModel,
        okHttpClient: OkHttpClient,
        pickyCookieJar: PickyCookieJar,
        uiThreadHandler: Handler
) : ISignupModel {

    private val JSON_MEDIATYPE: MediaType = MediaType.parse("application/json; charset=utf-8")!!

    private var presenter : ISignupPresenter.forModel = signupPresenter
    private var client : OkHttpClient = okHttpClient
    private var pickyCookieJar: PickyCookieJar = pickyCookieJar
    private var uiThreadHandler: Handler = uiThreadHandler



    override fun checkUsernameValid(newUsername: String) {
        var checkUsernameValidRequest: Request = Request.Builder()
                .url("http://192.168.0.18:9000/users/isUsernameValid?newUsername=${newUsername}")
                .build()
        client.newCall(checkUsernameValidRequest).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("VINIT", "Request failure: ${e.localizedMessage}")
            }

            override fun onResponse(call: Call, response: Response) {
                val jsonString: String = response?.body()?.string() ?: ""
                val respJson: JSONObject = JSONObject(jsonString)
                uiThreadHandler.post{ presenter.onUsernameValidUpdate(respJson.getString("message") ?: "") }

            }

        })
    }

    override fun updateUsername(newUsername: String) {
        var bodyJson: JSONObject = JSONObject()
        bodyJson.put("newUsername", newUsername)
        client.newCall(
                Request.Builder()
                        .url("http://192.168.0.18:9000/users/updateUsername")
                        .post(RequestBody.create(JSON_MEDIATYPE, bodyJson.toString()))
                        .build()
        ).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                // FIXME: it could say "Unauthorized" instead of json object
                uiThreadHandler.post {
                    presenter.onUsernameSetUpdate(getJsonObj(response).getString("message")
                            ?: "check your internet")
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                presenter.onUsernameSetUpdate("check your internet")
            }

        })
    }

    fun getJsonObj(r: Response): JSONObject {
        try {
            return JSONObject(r.body()?.string() ?: "{}")
        } catch (e: JSONException) {
            return JSONObject("{}")
        }
    }
}