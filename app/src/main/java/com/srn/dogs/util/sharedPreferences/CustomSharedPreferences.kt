package com.srn.dogs.util.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class CustomSharedPreferences {
    companion object{
        private val time ="time"
        private var sharedPreferences:SharedPreferences?=null

        @Volatile private var instance:CustomSharedPreferences?= null

        private val lock=Any()

        operator fun invoke(context: Context):CustomSharedPreferences= instance ?: synchronized(lock){
            instance ?: createSharedPreferences(context).also {
                instance=it
            }
        }


        private fun createSharedPreferences(context: Context):CustomSharedPreferences{
            sharedPreferences =androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPreferences()
        }



    }
    fun timeSave(time:Long){
        sharedPreferences?.edit(commit =true){
            putLong("time",time)
        }
    }
}