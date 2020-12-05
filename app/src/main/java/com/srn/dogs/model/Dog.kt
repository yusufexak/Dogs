package com.srn.dogs.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Dog(
    @ColumnInfo(name="code")
    @SerializedName("code")
    val code:Int?,
    @ColumnInfo(name="description")
    @SerializedName("description")
    val description:String?,
    @ColumnInfo(name="imageUrl")
    @SerializedName("imageUrl")
    val imageUrl:String?) {
    @PrimaryKey(autoGenerate = true)
    var uuid : Int =0
}
