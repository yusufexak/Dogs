package com.srn.dogs.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.srn.dogs.model.Dog

class DogDetailViewModel :ViewModel(){
    val dog= MutableLiveData<Dog>()

    fun roomDataGet(){
        val dog5=Dog(555,"555qwereqwe desp1 desp1desp1 desp1 desp1desp1 desp1 desp1desp1 desp1 desp1desp1 desp1 desp1desp1 desp1 desp1desp1 desp1 desp1","")
        dog.value=dog5

    }
}