package com.srn.dogs.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.srn.dogs.model.Dog

class DogListViewModel:ViewModel() {
    val dogs = MutableLiveData<List<Dog>>()
    val dogErrorMessage = MutableLiveData<Boolean>()
    val dogLoading = MutableLiveData<Boolean>()

    fun refleshData(){
        val dog1=Dog("200","desp1 desp1 desp1desp1 desp1 desp1desp1 desp1 desp1desp1 desp1 desp1desp1 desp1 desp1desp1 desp1 desp1desp1 desp1 desp1","")
        val dog2=Dog("222","222 desp1 desp1desp1 desp1 desp1desp1 desp1 desp1desp1 desp1 desp1desp1 desp1 desp1desp1 desp1 desp1desp1 desp1 desp1","")
        val dog3=Dog("333","33 ererer desp1 desp1desp1 desp1 desp1desp1 desp1 desp1desp1 desp1 desp1desp1 desp1 desp1desp1 desp1 desp1desp1 desp1 desp1","")
        val dog4=Dog("444","444 deswerwewerwerp1 desp1desp1 desp1 desp1desp1 desp1 desp1desp1 desp1 desp1desp1 desp1 desp1desp1 desp1 desp1desp1 desp1 desp1","")
        val dog5=Dog("555","555qwereqwe desp1 desp1desp1 desp1 desp1desp1 desp1 desp1desp1 desp1 desp1desp1 desp1 desp1desp1 desp1 desp1desp1 desp1 desp1","")

        val dogsList= arrayListOf<Dog>(dog1,dog2,dog3,dog4,dog5)
        dogs.value=dogsList
        dogErrorMessage.value=false
        dogLoading.value=false

    }
}