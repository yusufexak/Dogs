package com.srn.dogs.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Dog(
    @PrimaryKey(autoGenerate = true)
    val uuid : Int ,
    @SerializedName("code")
    @ColumnInfo(name="code")
    val code:Int?,
    @SerializedName("description")
    @ColumnInfo(name="description")
    val description:String?,
    @SerializedName("imageUrl")
    @ColumnInfo(name="imageUrl")
    val imageUrl:String?
    )
