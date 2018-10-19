package com.example.picky.picky.login

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.picky.picky.R
import com.example.picky.picky.login.di.DaggerLoginActivityComponent
import com.example.picky.picky.login.di.LoginActivityComponent
import com.example.picky.picky.login.interfacing.ILoginPresenter
import com.example.picky.picky.login.interfacing.ILoginView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

class LoginActivity : AppCompatActivity(), ILoginView {

    private val NEW_USER_REQUEST_CODE: Int = 1 // 1: Request code to return username and password from the SignupActivity
    private val GOOGLE_SIGN_IN: Int = 466 // 466: Request code for Google sign in

    @Inject
    lateinit var loginPresenter: ILoginPresenter.forView

    private val context: Context = this

    private lateinit var gso: GoogleSignInOptions

    private lateinit var googleSignInClient: GoogleSignInClient

    override fun loginResult(status: String, username: String) {
        Toast.makeText(this, status + ": " + username, Toast.LENGTH_SHORT).show()
        // TODO: check login status and send to next activity with username
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        googleSignInBtn.setOnClickListener {
            signInWithGoogle()
        }

        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.google_oauth_client_id))
                .requestProfile()
                .build()

        googleSignInClient = GoogleSignIn.getClient(context, gso)

        var loginActivityComponent: LoginActivityComponent = DaggerLoginActivityComponent
                .builder()
                .build()
        loginActivityComponent.injectLoginActivity(this)



//        var contextComponent: ContextComponent = DaggerContextComponent
//                .builder()
//                .networkComponent((application as PickyApplication).app(this).networkComponent)
//                .contextModule(ContextModule(this))
//                .build()
//        contextComponent.injectLoginActivity(this)



        val lastGoogleAccount: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(context)

        if (lastGoogleAccount != null) {
            Log.d("VINIT", "aithing..")
            loginPresenter.authWithGoogle(lastGoogleAccount.idToken ?: "")
        } else {
            // TODO: account not found
        }
    }

    private fun login() {
        // FIXME: Sign in with OAuth
        loginPresenter.loginWith("")
    }

    private fun signInWithGoogle() {
        // TODO: sign in with Google
        startActivityForResult(googleSignInClient.signInIntent, GOOGLE_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleGoogleSignInResult(task)
        }
    }

    private fun handleGoogleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val googleSignInAccount: GoogleSignInAccount = task.getResult(ApiException::class.java)
            Log.d("VINIT", googleSignInAccount.displayName)
            loginPresenter.authWithGoogle(googleSignInAccount.idToken ?: "")
        } catch (e: Exception) {
            Log.d("VINIT", e.message)
        }
    }

    companion object {
        fun getContext(loginActivity: LoginActivity): Context {
            return loginActivity
        }
    }

}
