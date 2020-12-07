package com.srn.dogs.util.extension

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.srn.dogs.R
import com.srn.dogs.util.functions.placeHolderCreate

fun ImageView.imageDownload(url:String?,placaHolder:CircularProgressDrawable){
    val options =RequestOptions().placeholder(placaHolder).error(R.mipmap.ic_launcher)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)

}


