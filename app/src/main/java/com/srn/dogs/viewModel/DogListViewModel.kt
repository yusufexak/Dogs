package com.srn.dogs.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.srn.dogs.base.BaseViewModel
import com.srn.dogs.model.Dog
import com.srn.dogs.service.API.DogAPIService
import com.srn.dogs.service.DAO.DogDatabase
import com.srn.dogs.util.sharedPreferences.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class DogListViewModel(application: Application):BaseViewModel(application) {
    val dogs = MutableLiveData<List<Dog>>()
    val dogErrorMessage = MutableLiveData<Boolean>()
    val dogLoading = MutableLiveData<Boolean>()

    private var updateTime = 0.2*60*1000*1000*1000L

    private val dogApiService= DogAPIService()
    private val disposable=CompositeDisposable()
    private val customSH=CustomSharedPreferences(getApplication())

    fun refleshData(){
        val saveTime = customSH.getTime()
        if (saveTime !=null && saveTime!=0L && System.nanoTime()-saveTime<updateTime){
            getDataDatabase()
        }else {
            getDataNetwork()
        }

    }
    fun refleshFromNetwork(){
        getDataNetwork()
    }

    private  fun getDataDatabase(){
        launch {
            val dogsList= DogDatabase(getApplication()).dogDao().getAllDogs()
            dogsVisibil(dogsList)
            Toast.makeText(getApplication(),"Room",Toast.LENGTH_LONG).show()
        }
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
                        Toast.makeText(getApplication(),"Network",Toast.LENGTH_LONG).show()
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
            val dao = DogDatabase(getApplication()).dogDao()
            dao.deleteAllDogs()
            val uuidList= dao.insertAll(*dogsList.toTypedArray())
            var i=0
            while (i<dogsList.size){
                dogsList[i].uuid=uuidList[i].toInt()
                i=i+1
            }
            dogsVisibil(dogsList)
        }
        customSH.timeSave(System.nanoTime())
    }
}