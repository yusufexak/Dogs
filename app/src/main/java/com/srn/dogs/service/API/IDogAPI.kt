package com.srn.dogs.service.API

import com.srn.dogs.model.Dog
import io.reactivex.Single
import retrofit2.http.GET

interface IDogAPI {
    //https://hwasampleapi.firebaseio.com/dogs.json
    //BASE_URL -> hwasampleapi.firebaseio.com/

    @GET("dogs.json")
    fun getDogs(): Single<List<Dog>>
}