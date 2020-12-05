package com.srn.dogs.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.srn.dogs.base.BaseViewModel
import com.srn.dogs.model.Dog
import com.srn.dogs.service.DogAPIService
import com.srn.dogs.service.DogDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class DogListViewModel(application: Application):BaseViewModel(application) {
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

                        sqlSave(t)
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
    private fun dogsVisibil(dogsList:List<Dog>){
        dogs.value=dogsList
        dogErrorMessage.value=false
        dogLoading.value=false
    }

    private fun sqlSave(dogsList:List<Dog>){
        launch {
            val dao =DogDatabase(getApplication()).dogDao()
            dao.deleteAllDogs()
            val uuidList= dao.insertAll(*dogsList.toTypedArray())
            var i=0
            while (i<dogsList.size){
                dogsList[i].uuid=uuidList[i].toInt()
                i=i+1
            }
            dogsVisibil(dogsList)
        }
    }
}