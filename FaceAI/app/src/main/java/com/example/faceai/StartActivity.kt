package com.example.faceai

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import java.util.*

class StartActivity : AppCompatActivity() {
    var loggedOut: Boolean = false

    val EMAIL = "email"
    val PUBLIC_PROFILE = "public_profile"

    var callbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
        setContentView(R.layout.activity_start)
        loggedOut = AccessToken.getCurrentAccessToken() == null || Profile.getCurrentProfile() == null
        if (!loggedOut) {
//            val intent = Intent(this@LoginActivity, CardActivity::class.java)
//            startActivity(intent)
        }
        val loginButton = findViewById<LoginButton>(R.id.login_button)

        loginButton.setReadPermissions(Arrays.asList(EMAIL, PUBLIC_PROFILE))
        callbackManager = CallbackManager.Factory.create()

        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                getUserProfile(loginResult.accessToken)
            }

            override fun onCancel() {
                println("penis")
            }

            override fun onError(error: FacebookException) {
                println(error)
            }
        })

    }

    private fun getUserProfile(currentAccessToken: AccessToken) {
        val request = GraphRequest.newMeRequest(
            currentAccessToken
        ) { `object`, response ->
            val intent = Intent(this@StartActivity, MainActivity::class.java)
            startActivity(intent)
        }

        val parameters = Bundle()
        parameters.putString("fields", "first_name,last_name,email,id")
        request.parameters = parameters
        request.executeAsync()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }
}
