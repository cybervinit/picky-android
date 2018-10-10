package com.example.picky.picky.phoneVerification

import android.content.Context
import android.net.Uri
import com.example.picky.picky.phoneVerification.interfacing.IPhoneVerificationModel
import com.example.picky.picky.phoneVerification.interfacing.IPhoneVerificationPresenter
import org.json.JSONObject

/**
 * Created by vinitsoni on 2018-08-22.
 */
class PhoneVerificationModel(
        pvPresenter: IPhoneVerificationPresenter.forModel,
        c: Context
): IPhoneVerificationModel {

    private var pvPresenter: IPhoneVerificationPresenter.forModel = pvPresenter
    private var context: Context = c

    override fun requestPhoneVerification(phoneNumber: String, countryCode: String, via: String) {
//        val queue = Volley.newRequestQueue(context)
        var params: JSONObject = JSONObject()
        params.put("phoneNumber", phoneNumber)
        params.put("countryCode", countryCode)
        params.put("via", via)

//        val req: JsonObjectRequest = JsonObjectRequest(Request.Method.POST, "https://pickystaging.herokuapp.com/users/requestPhoneVerification", params,
//                Response.Listener { resp ->
//                    if (!resp.getBoolean("success")) {
//                        // TODO: remove this log
//                        // TODO: request failed dialog for the user?
//                        Log.d("VINIT", resp.getString("message"))
//                    }
//                }, Response.ErrorListener { err ->
//                    // TODO: request failed dialog for the user?
//                    Log.d("VINIT", err.message)
//        })
//        queue.add(req)
    }

    override fun verifyToken(token: String, countryCode: String, phoneNumber: String) {
//        val queue = Volley.newRequestQueue(context)
        val uriBuilder: Uri.Builder = Uri.Builder()
        uriBuilder.scheme("https")
                .authority("pickystaging.herokuapp.com")
                .appendPath("users")
                .appendPath("verifyPhoneToken")
                .appendQueryParameter("verifyToken", token)
                .appendQueryParameter("countryCode", countryCode)
                .appendQueryParameter("phoneNumber", phoneNumber)
        val url: String = uriBuilder.build().toString()


        // FIXME: impl the network request to verify phone
        pvPresenter.onPhoneVerifyStatusUpdated(true)
//        val req: JsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
//                Response.Listener { resp ->
//                    val isVerified = resp.getBoolean("success")
//                    if (!isVerified) {
//                        // TODO: remove this log
//                        Log.d("VINIT", resp.getString("message"))
//                    }
//                    pvPresenter.onPhoneVerifyStatusUpdated(isVerified)
//                }, Response.ErrorListener { err ->
//
//                    Log.d("VINIT", err.toString())
//                    pvPresenter.onPhoneVerifyStatusUpdated(false)
//        })
//        queue.add(req)
    }

}