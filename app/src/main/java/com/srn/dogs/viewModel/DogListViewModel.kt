package com.srn.dogs.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.srn.dogs.model.Dog
import com.srn.dogs.service.DogAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DogListViewModel:ViewModel() {
    val dogs = MutableLiveData<List<Dog>>()
    val dogErrorMessage = MutableLiveData<Boolean>()
    val dogLoading = MutableLiveData<Boolean>()

    private val dogApiService=DogAPIService()
    private val disposable=CompositeDisposable()

    fun refleshData(){
        getDataNetwork()


    }
    private fun getDataNetwork(){
        dogLoading.value=true
        disposable.add(
            dogApiService.getDogData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<List<Dog>>(){
                    override fun onSuccess(t: List<Dog>) {
                        dogs.value=t
                        dogErrorMessage.value=false
                        dogLoading.value=false
                    }

                    override fun onError(e: Throwable) {
                        dogErrorMessage.value=true
                        dogLoading.value=false
                        e.printStackTrace()
                    }

                }
            )
        )
    }
}