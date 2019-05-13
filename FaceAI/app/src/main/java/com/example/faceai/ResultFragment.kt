package com.example.faceai


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_result.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.support.v7.widget.DividerItemDecoration
import android.widget.LinearLayout.VERTICAL
import android.R.attr.divider




class ResultFragment : Fragment() {
    private var mAdapter: RecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    val arr: ArrayList<String> = arrayListOf(
        "https://24tv.ua/resources/photos/news/610x344_DIR/201903/1134593.jpg?201904125300",
        "https://www.jaagore.com/sites/default/files/images/Rekha%20Kalindi%20final.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/JohnnyDepp2018.jpg/267px-JohnnyDepp2018.jpg")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = User()
       // user.id = arguments?.get("id") as String
        val persons = ArrayList<Person>()
        val person: Person = Person()
        val person1: Person = Person()
        val person2: Person = Person()
        person.photo64 = arr[0]
        person1.photo64 = arr[1]
        person2.photo64 = arr[2]
        person.firstName = "Cortney Cox"
        person1.firstName = "Cortney Cox"
        person2.firstName = "Cortney Cox"
        person.email= "Cortney@Cox"
        person1.email = "Cortney@Cox"
        person2.email = "Cortney@Cox"
        persons.add(person)
        persons.add(person1)
        persons.add(person2)
//        val gson = GsonBuilder()
//            .setLenient()
//            .create()
//
//
//
//        val BASE_URL = "http://3.82.61.171/"
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//
//        val api: MyApiEndpointInterface = retrofit.create(MyApiEndpointInterface::class.java)
//        val call = api.getPersons(user)
//        call.enqueue(object : Callback<ArrayList<Person>> {
//            override fun onResponse(call: Call<ArrayList<Person>>, response: Response<ArrayList<Person>>) {
//                if(response.body() != null)
//                response.body()?.let { persons.addAll(it) }
                mAdapter = RecyclerViewAdapter(persons)
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(this.context)
                    adapter = mAdapter

                }
//                recyclerView.addItemDecoration(
//                    DividerItemDecoration(activity!!, R.drawable.divider_horizontal_bright)
//                )
//            }
//
//            override fun onFailure(call: Call<ArrayList<Person>>, t: Throwable) {
//                println(t)
//            }
//        })

        button4.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id", user.id)
            Navigation.findNavController(view).navigate(R.id.quizFragment)
        }

    }

}
