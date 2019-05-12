package com.example.faceai


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_registration.*
import android.content.Intent
import android.provider.MediaStore
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.navigation.Navigation
import com.facebook.Profile
import kotlinx.android.synthetic.main.fragment_registration.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import com.google.gson.GsonBuilder
import com.google.gson.Gson






class RegistrationFragment : Fragment() {

    lateinit var firstName: String
    lateinit var lastName: String
    lateinit var email: String
    lateinit var encoded: String

    private val CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firstName = Profile.getCurrentProfile().firstName
        lastName = Profile.getCurrentProfile().lastName
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        email = activity?.intent?.extras?.get("EMAIL") as String
        button2.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra("android.intent.extras.CAMERA_FACING", 1)
            this.startActivityForResult(
                intent,
                CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE
            )
        }



        button3.setOnClickListener {

            val gender = if (checkBox.isChecked) "male" else "female"
            val pref: String = when {
                checkBox3.isChecked -> "male"
                checkBox4.isChecked -> "female"
                else -> "both"
            }

            val person = Person()
            person.email = email
            person.firstName = firstName
            person.lastName = lastName
            person.gender = email
            person.photo64 = encoded
            person.pref = pref

            val gson = GsonBuilder()
                .setLenient()
                .create()

            val BASE_URL = "http://3.82.61.171/"
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            val api: MyApiEndpointInterface = retrofit.create(MyApiEndpointInterface::class.java)
            val call = api.createUser(person)
            call.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                   val user = response.body()
                    val bundle = Bundle()
                    bundle.putString("id", user?.id)
                    Navigation.findNavController(view).navigate(R.id.quizFragment, bundle)
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    println(t)
                }
            })

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                var bmp: Bitmap = data?.getExtras()?.get("data") as Bitmap
                var stream = ByteArrayOutputStream()

                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream)

                var byteArray: ByteArray = stream.toByteArray()

                // convert byte array to Bitmap

                encoded = Base64.encodeToString(byteArray, Base64.DEFAULT)

                var bitmap = BitmapFactory.decodeByteArray(
                    byteArray, 0,
                    byteArray.size
                )

                imageChat.setImageBitmap(bitmap)//
            }
        }
    }
}
