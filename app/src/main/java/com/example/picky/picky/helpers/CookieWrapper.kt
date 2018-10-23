package com.example.picky.picky.helpers

import android.util.Base64
import okhttp3.Cookie
import org.json.JSONException
import org.json.JSONObject

class CookieWrapper(
        cookie: Cookie
) {
    private val cookie: Cookie = cookie

    public val cookieValue: String = cookie.value()
    public val decodedValue: String = String(Base64.decode(cookieValue, Base64.DEFAULT))
    public lateinit var jsonObject: JSONObject
    public val cookieName: String = cookie.name()

    init {
        try {
            jsonObject = JSONObject(String(Base64.decode(cookieValue, Base64.DEFAULT)))
        } catch (e: JSONException) {
            jsonObject = JSONObject("{}")
        }
    }
}