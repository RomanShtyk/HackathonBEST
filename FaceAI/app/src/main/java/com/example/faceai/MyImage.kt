package com.example.faceai

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MyImage{

    @SerializedName("photo")
    @Expose
    lateinit var photo64: String

//    @SerializedName("id")
//    @Expose
//    lateinit var id : String

}