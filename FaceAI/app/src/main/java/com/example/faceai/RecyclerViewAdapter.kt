package com.example.faceai

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_quiz.*
import kotlinx.android.synthetic.main.person_item.view.*

class RecyclerViewAdapter(var items: ArrayList<Person>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>(){

    init{
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.person_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getItem(position: Int): Person {
        return items[position]
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val person: Person = items[position]
        Picasso.get().load(person.photo64).into(holder.image)

        holder.name.text = person.firstName
        holder.bio.text = person.email
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){

        var image: ImageView = itemView.image
        var name: TextView = itemView.userName
        var bio: TextView = itemView.lastMessage


    }

    override fun getItemId(position: Int): Long = position.toLong()


}
