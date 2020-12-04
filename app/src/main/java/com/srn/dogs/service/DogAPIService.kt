package com.srn.dogs.service

import com.srn.dogs.model.Dog
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DogAPIService {
    //https://hwasampleapi.firebaseio.com/dogs.json
    //BASE_URL -> hwasampleapi.firebaseio.com/
    private val BASE_URL = "https://hwasampleapi.firebaseio.com/"
    private  val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(IDogAPI::class.java)

    fun getDogData():Single<List<Dog>>{
        return api.getDogs()
    }
}