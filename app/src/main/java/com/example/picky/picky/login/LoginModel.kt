package com.example.picky.picky.login

import android.util.Log
import com.example.picky.picky.login.interfacing.ILoginModel
import com.example.picky.picky.login.interfacing.ILoginPresenter
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

/**
 * Created by vinitsoni on 2018-04-15.
 */
class LoginModel @Inject constructor(
        loginPresenter: ILoginPresenter.forModel,
        okHttpClient: OkHttpClient
) : ILoginModel {

    private val JSON_MEDIATYPE: MediaType = MediaType.parse("application/json; charset=utf-8")!!

    private var loginPresenter: ILoginPresenter.forModel = loginPresenter

    override fun loginWith(id_token: String) {

    }

    override fun authWithGoogle(idToken: String) {
//        loginPresenter.loginResult("success", username)
        var reqJson: JSONObject = JSONObject()
        reqJson.put("id_token", idToken)
        val loginReqBody: RequestBody = RequestBody.create(JSON_MEDIATYPE, reqJson.toString())
        var loginRequest: Request = Request.Builder()
                .url("http://192.168.0.16:9000/auth/google/android")
                .post(loginReqBody)
                .build()
        var client = OkHttpClient()
        client.newCall(loginRequest).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("VINIT", "Request failure: ${e.localizedMessage}")
            }

            override fun onResponse(call: Call, response: Response) {
                val jsonString: String = response.body()?.string() ?: ""
                if (!(response.headers("Set-Cookie")?.isEmpty() ?: true)) {
                    Log.d("VINIT", "Cookie Values beneath")
                    for (item in response.headers("Set-Cookie")) {
                        Log.d("VINIT", item)
                    }
//                    Log.d("VINIT", response.header("Set-Cookie") ?: "")
                }
                Log.d("VINIT", jsonString)
            }

        })
    }
}