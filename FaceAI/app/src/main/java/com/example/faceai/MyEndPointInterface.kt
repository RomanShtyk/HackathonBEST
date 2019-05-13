package com.example.faceai

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface MyApiEndpointInterface {

    @POST("get_user")
    fun createUser(@Body person: Person): Call<User>

    @POST("get_images")
    fun getImages(@Body user: User): Call<MyImage>

    @POST("hz")
    fun sendChosenImage(@Body image: MyImage)

    @GET("get_matchers")
    fun getPersons(@Body user: User) : Call<ArrayList<Person>>

}