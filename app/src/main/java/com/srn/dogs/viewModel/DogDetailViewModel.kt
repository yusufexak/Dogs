package com.srn.dogs.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.srn.dogs.base.BaseViewModel
import com.srn.dogs.model.Dog
import com.srn.dogs.service.DAO.DogDatabase
import kotlinx.coroutines.launch

class DogDetailViewModel(application: Application) :BaseViewModel(application){
    val dog= MutableLiveData<Dog>()

    fun roomDataGet(uuid:Int){
        launch {
            val dao =DogDatabase(getApplication()).dogDao()
            val dogData = dao.getDogs(uuid)
            dog.value=dogData
        }

    }
}