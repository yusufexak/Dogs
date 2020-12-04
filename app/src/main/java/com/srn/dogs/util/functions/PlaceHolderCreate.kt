package com.srn.dogs.util.functions

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable


fun placeHolderCreate(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth=8f
        centerRadius=40f
        start()
    }
}