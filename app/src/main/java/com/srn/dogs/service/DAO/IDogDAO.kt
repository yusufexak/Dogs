package com.srn.dogs.service.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.srn.dogs.model.Dog

@Dao
interface IDogDAO {
    @Insert
    suspend fun insertAll(vararg dog:Dog):List<Long>

    @Query("SELECT * FROM dog")
    suspend fun getAllDogs():List<Dog>

    @Query("SELECT * FROM dog WHERE uuid=:dogId")
    suspend fun getDogs(dogId:Int):Dog

    @Query("DELETE FROM dog")
    suspend fun deleteAllDogs():Dog
}