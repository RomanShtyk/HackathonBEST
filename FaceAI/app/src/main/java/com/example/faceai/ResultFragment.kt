package com.example.faceai


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_result.*

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
        val persons = ArrayList<Person>()
        mAdapter = RecyclerViewAdapter(persons)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
        }

//        mAdapter = RecyclerViewAdapter(records, this@ListFragment)
//        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
//        divider.setDrawable(context!!.getDrawable(R.drawable.recyclerview_divider))
//        recyclerView.apply {
//            layoutManager = LinearLayoutManager(this.context)
//            adapter = mAdapter
//            addItemDecoration(divider)
//        }

    }



}
