package com.example.faceai


import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList
import android.util.Base64
import androidx.navigation.Navigation
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_quiz.*


class QuizFragment : Fragment() {

    private val BASE_URL = "http://3.82.61.171/"

    lateinit var retrofit: Retrofit

    private lateinit var gson: Gson

    lateinit var api: MyApiEndpointInterface

    lateinit var arr: ArrayList<MyImage>

    var counter = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = User()
        user.id = arguments?.get("id") as String
        gson = GsonBuilder()
            .setLenient()
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        api = retrofit.create(MyApiEndpointInterface::class.java)
        getUsers(user)

        textView5.text = "$counter/7"

        imageView.setOnClickListener {
            val call = api.sendChosenImage(arr[0])
            getUsers(user)
            counter++
            if (counter == 8) {
                val bundle = Bundle()
                bundle.putString("id", user.id)
                Navigation.findNavController(view).navigate(R.id.quizFragment, bundle)
            } else
            textView5.text = "$counter/7"
        }
        imageView2.setOnClickListener {
            val call = api.sendChosenImage(arr[0])
            getUsers(user)
            counter++
            if (counter == 8) {
                val bundle = Bundle()
                bundle.putString("id", user.id)
                Navigation.findNavController(view).navigate(R.id.quizFragment, bundle)
            } else
            textView5.text = "$counter/7"
        }
        imageView4.setOnClickListener {
            val call = api.sendChosenImage(arr[0])
            getUsers(user)
            counter++
            if (counter == 8) {
                val bundle = Bundle()
                bundle.putString("id", user.id)
                Navigation.findNavController(view).navigate(R.id.quizFragment, bundle)
            } else
            textView5.text = "$counter/7"
        }
        imageView5.setOnClickListener {
            val call = api.sendChosenImage(arr[0])
            getUsers(user)
            counter++
            if (counter == 8) {
                val bundle = Bundle()
                bundle.putString("id", user.id)
                Navigation.findNavController(view).navigate(R.id.quizFragment, bundle)
            } else
            textView5.text = "$counter/7"
        }
        imageView6.setOnClickListener {
            val call = api.sendChosenImage(arr[0])
            getUsers(user)
            counter++
            if (counter == 8) {
                val bundle = Bundle()
                bundle.putString("id", user.id)
                Navigation.findNavController(view).navigate(R.id.quizFragment, bundle)
            } else
            textView5.text = "$counter/7"
        }
        imageView7.setOnClickListener {
            val call = api.sendChosenImage(arr[0])
            getUsers(user)
            counter++
            if (counter == 8) {
                val bundle = Bundle()
                bundle.putString("id", user.id)
                Navigation.findNavController(view).navigate(R.id.quizFragment, bundle)
            } else
            textView5.text = "$counter/7"
        }
    }


    private fun getUsers(user: User) {
        val call = api.getImages(user)
        call.enqueue(object : Callback<ArrayList<MyImage>> {

            override fun onResponse(call: Call<ArrayList<MyImage>>, response: Response<ArrayList<MyImage>>) {
                if (response.body() != null) {
                    arr = response.body()!!
                    var i = 0
                    arr.forEach {
                        val bmp = Base64.decode(it.photo64, Base64.DEFAULT)

                        val bitmap = BitmapFactory.decodeByteArray(
                            bmp, 0,
                            bmp.size
                        )
                        when (i) {
                            0 -> imageView.setImageBitmap(bitmap)
                            1 -> imageView2.setImageBitmap(bitmap)
                            2 -> imageView4.setImageBitmap(bitmap)
                            3 -> imageView5.setImageBitmap(bitmap)
                            4 -> imageView6.setImageBitmap(bitmap)
                            5 -> imageView7.setImageBitmap(bitmap)
                        }
                        i++
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<MyImage>>, t: Throwable) {
                println(t)
            }
        })
    }
}
