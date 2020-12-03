package com.srn.dogs.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.srn.dogs.model.Dog

class DogListViewModel:ViewModel() {
    val dogs = MutableLiveData<List<Dog>>()
    val dogErrorMessage = MutableLiveData<Boolean>()
    val dogLoading = MutableLiveData<Boolean>()


}