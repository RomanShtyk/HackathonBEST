package com.example.faceai


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.*
import com.facebook.login.LoginResult
import java.util.*

import com.facebook.GraphRequest
import com.facebook.AccessToken
import com.facebook.login.widget.LoginButton
import android.content.Intent








class LoginFragment : Fragment() {
    var loggedOut: Boolean = false

    val EMAIL = "email"
    val PUBLIC_PROFILE = "public_profile"

    var callbackManager: CallbackManager? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       val  view = inflater.inflate(R.layout.fragment_login, container, false)

        loggedOut = AccessToken.getCurrentAccessToken() == null || Profile.getCurrentProfile() == null
        if (!loggedOut) {
//            val intent = Intent(this@LoginActivity, CardActivity::class.java)
//            startActivity(intent)
        }
        val loginButton = view.findViewById<LoginButton>(R.id.login_button)

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




        return view
    }

    private fun getUserProfile(currentAccessToken: AccessToken) {
        val request = GraphRequest.newMeRequest(
            currentAccessToken
        ) { `object`, response ->
            //перехід далі
        }

        val parameters = Bundle()
        parameters.putString("fields", "first_name,last_name,email,id")
        request.parameters = parameters
        request.executeAsync()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
            this.onActivityResult(requestCode, resultCode, data)
        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }




}
