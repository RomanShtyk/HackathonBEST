package com.example.faceai


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.ArrayList
import androidx.navigation.Navigation
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_quiz.*


class QuizFragment : Fragment() {

    private val BASE_URL = "http://3.82.61.171/"

    lateinit var retrofit: Retrofit

    private lateinit var gson: Gson

    lateinit var api: MyApiEndpointInterface

    val arr: ArrayList<String> = arrayListOf(
        "https://gordonua.com/img/article/9511/62_tn.jpg",
        "https://rozmova.files.wordpress.com/2019/04/227824.jpg?w=303&h=200",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/JohnnyDepp2018.jpg/267px-JohnnyDepp2018.jpg",
        "https://top10.ucoz.ua/gala/217202574.jpg",
        "https://24tv.ua/resources/photos/news/610x344_DIR/201903/1134593.jpg?201904125300",
        "https://lifeimg.pravda.com/images/doc/4/4/44d77f0-3.jpg",
        "https://gordonua.com/img/article/9511/62_tn.jpg",
        "https://24tv.ua/resources/photos/news/610x344_DIR/201903/1134593.jpg?201904125300",
        "https://lifeimg.pravda.com/images/doc/4/4/44d77f0-3.jpg",
        "https://www.jaagore.com/sites/default/files/images/Rekha%20Kalindi%20final.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/JohnnyDepp2018.jpg/267px-JohnnyDepp2018.jpg",
        "https://gordonua.com/img/article/9511/62_tn.jpg",
        "http://img.viva.ua/pictures/uploads/images/GettyImages-814030908.jpg", "https://24tv.ua/resources/photos/news/610x344_DIR/201903/1134593.jpg?201904125300", "https://lifeimg.pravda.com/images/doc/4/4/44d77f0-3.jpg", "https://www.jaagore.com/sites/default/files/images/Rekha%20Kalindi%20final.jpg",  "https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/JohnnyDepp2018.jpg/267px-JohnnyDepp2018.jpg", "https://gordonua.com/img/article/9511/62_tn.jpg",
        "http://img.viva.ua/pictures/uploads/images/GettyImages-814030908.jpg", "https://24tv.ua/resources/photos/news/610x344_DIR/201903/1134593.jpg?201904125300", "https://lifeimg.pravda.com/images/doc/4/4/44d77f0-3.jpg", "https://www.jaagore.com/sites/default/files/images/Rekha%20Kalindi%20final.jpg",  "https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/JohnnyDepp2018.jpg/267px-JohnnyDepp2018.jpg", "https://gordonua.com/img/article/9511/62_tn.jpg")

    var counter = 1
    var counter2= 0

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
        //user.id = arguments?.get("id") as String
        gson = GsonBuilder()
            .setLenient()
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        api = retrofit.create(MyApiEndpointInterface::class.java)
        getUsers()

        textView5.text = "$counter/2"

        imageView.setOnClickListener {
            //            val call = api.sendChosenImage(arr)
            getUsers()
            counter++
            if (counter > 2) {
                val bundle = Bundle()
               // bundle.putString("id", user.id)
                Navigation.findNavController(view).navigate(R.id.resultFragment)
            } else
                textView5.text = "$counter/2"
        }
        imageView2.setOnClickListener {
            //            val call = api.sendChosenImage(arr)
            getUsers()
            counter++
            if (counter > 2) {
                val bundle = Bundle()
               // bundle.putString("id", user.id)
                Navigation.findNavController(view).navigate(R.id.resultFragment)
            } else
                textView5.text = "$counter/2"
        }
        imageView4.setOnClickListener {
            //            val call = api.sendChosenImage(arr)
            getUsers()
            counter++
            if (counter > 2) {
                val bundle = Bundle()
                //bundle.putString("id", user.id)
                Navigation.findNavController(view).navigate(R.id.resultFragment)
            } else
                textView5.text = "$counter/2"
        }
        imageView5.setOnClickListener {
            //            val call = api.sendChosenImage(arr)
            getUsers()
            counter++
            if (counter > 2) {
                val bundle = Bundle()
                Navigation.findNavController(view).navigate(R.id.resultFragment)
            } else
                textView5.text = "$counter/2"
        }
        imageView6.setOnClickListener {
            //            val call = api.sendChosenImage(arr)
            getUsers()
            counter++
            if (counter > 2) {
                val bundle = Bundle()
                Navigation.findNavController(view).navigate(R.id.resultFragment)
            } else
                textView5.text = "$counter/2"
        }
        imageView7.setOnClickListener {
            //            val call = api.sendChosenImage(arr)
            getUsers()
            counter++
            if (counter > 2) {
                val bundle = Bundle()
                Navigation.findNavController(view).navigate(R.id.resultFragment)
                // Navigation.findNavController(view).navigate(R.id.resultFragment, bundle)
            } else
                textView5.text = "$counter/2"
        }
    }


    private fun getUsers() {

        for (i in 1..6) {
            when (i) {
                1 -> {
                    Picasso.get().load(arr[counter2]).resize(130, 130).into(imageView)
                    counter2++
                }
                2 -> {
                    Picasso.get().load(arr[counter2]).resize(130, 130).into(imageView2)
                    counter2++
                }
                3 -> {
                    Picasso.get().load(arr[counter2]).resize(130, 130).into(imageView4)
                    counter2++
                }
                4 -> {
                    Picasso.get().load(arr[counter2]).resize(130, 130).into(imageView5)
                    counter2++
                }
                5 -> {
                    Picasso.get().load(arr[counter2]).resize(130, 130).into(imageView6)
                    counter2++
                }
                6 -> {
                    Picasso.get().load(arr[counter2]).resize(130, 130).into(imageView7)
                    counter2++
                }
            }
        }
    }
}
