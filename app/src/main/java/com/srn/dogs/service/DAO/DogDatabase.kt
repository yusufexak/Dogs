package com.srn.dogs.service.DAO

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.srn.dogs.model.Dog

@Database(entities = arrayOf(Dog::class),version = 1)
abstract class DogDatabase :RoomDatabase(){

    abstract fun dogDao(): IDogDAO

    companion object{

        @Volatile private  var instance: DogDatabase?=null

        private val lock =Any()

        operator fun invoke(context: Context)= instance ?: synchronized(lock){
            instance ?: createDatabase(context).also {
                instance =it
            }
        }


        private fun createDatabase(context:Context)= Room.databaseBuilder(
            context.applicationContext,
            DogDatabase::class.java,
            "dogdatabase").build()
    }
}