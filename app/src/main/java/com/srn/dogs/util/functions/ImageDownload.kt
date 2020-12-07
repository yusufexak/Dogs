package com.srn.dogs.util.functions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.srn.dogs.util.extension.imageDownload

@BindingAdapter("android:downloadImage")
fun downloadImage(view: ImageView, url:String?){
    view.imageDownload(url, placeHolderCreate(view.context))
}