package com.example.faceai

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Person{

    @SerializedName("photo")
    @Expose
    lateinit var photo64: String

    @SerializedName("firstname")
    @Expose
    lateinit var firstName : String

    @SerializedName("lastname")
    @Expose
    lateinit var lastName: String

    @SerializedName("email")
    @Expose
    lateinit var email: String

    @SerializedName("gender")
    @Expose
    lateinit var gender: String

    @SerializedName("pref")
    @Expose
    lateinit var pref: String
}