package com.example.picky.picky.login

import android.os.Handler
import android.util.Base64
import android.util.Log
import com.example.picky.picky.helpers.PickyCookieJar
import com.example.picky.picky.login.interfacing.ILoginModel
import com.example.picky.picky.login.interfacing.ILoginPresenter
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception
import java.nio.charset.StandardCharsets
import javax.inject.Inject

/**
 * Created by vinitsoni on 2018-04-15.
 */
class LoginModel constructor(
        loginPresenter: ILoginPresenter.forModel,
        okHttpClient: OkHttpClient,
        pickyCookieJar: PickyCookieJar,
        handler: Handler
) : ILoginModel {

    private val JSON_MEDIATYPE: MediaType = MediaType.parse("application/json; charset=utf-8")!!
    private var okHttpClient: OkHttpClient = okHttpClient
    private var loginPresenter: ILoginPresenter.forModel = loginPresenter
    private val handler: Handler = handler

    override fun authWithGoogle(idToken: String) {
        var reqJson: JSONObject = JSONObject()
        reqJson.put("id_token", idToken)
        val loginReqBody: RequestBody = RequestBody.create(JSON_MEDIATYPE, reqJson.toString())
        var loginRequest: Request = Request.Builder()
                .url("http://192.168.0.18:9000/auth/google/phone")
                .post(loginReqBody)
                .build()
        okHttpClient.newCall(loginRequest).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("VINIT", "Request failure: ${e.localizedMessage}")
                handler.post { loginPresenter.loginResult("check your internet connection") }
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val jsonString: String = response.body()?.string() ?: ""
                    val respObj: JSONObject = JSONObject(jsonString)
                    handler.post { loginPresenter.loginResult(respObj.getString("message")) }
                } catch (e: JSONException) {
                    Log.d("VINIT", e.localizedMessage)
                    handler.post { loginPresenter.loginResult("check your internet connection") }
                }
            }

        })
    }
}