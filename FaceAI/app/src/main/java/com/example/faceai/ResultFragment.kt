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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = User()
        user.id = arguments?.get("id") as String
        val persons = ArrayList<Person>()
        val gson = GsonBuilder()
            .setLenient()
            .create()



        val BASE_URL = "http://3.82.61.171/"
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val api: MyApiEndpointInterface = retrofit.create(MyApiEndpointInterface::class.java)
        val call = api.getPersons(user)
        call.enqueue(object : Callback<ArrayList<Person>> {
            override fun onResponse(call: Call<ArrayList<Person>>, response: Response<ArrayList<Person>>) {
                if(response.body() != null)
                response.body()?.let { persons.addAll(it) }
                mAdapter = RecyclerViewAdapter(persons)
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(this.context)

                }
                recyclerView.addItemDecoration(
                    DividerItemDecoration(activity!!, R.drawable.divider_horizontal_bright)
                )
            }

            override fun onFailure(call: Call<ArrayList<Person>>, t: Throwable) {
                println(t)
            }
        })

        button4.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id", user.id)
            Navigation.findNavController(view).navigate(R.id.quizFragment)
        }

    }

}
