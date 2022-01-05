package com.intern.test.util

import android.widget.ImageView
import com.squareup.picasso.Picasso

object Extension {

    fun ImageView.loadImage(url: String) {
        Picasso.get().load(url).into(this)
    }
}