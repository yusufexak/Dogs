package com.srn.dogs.model

import com.google.gson.annotations.SerializedName

data class Dog(
    @SerializedName("code")
    val code:String?,
    @SerializedName("description")
    val description:String?,
    @SerializedName("imageUrl")
    val imageUrl:String?) {
}
