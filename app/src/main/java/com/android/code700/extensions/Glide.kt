package com.android.code700.extensions

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


@SuppressLint("CheckResult")
inline fun ImageView.loadImage(
    url: String?, placeholder: Int? = null, errorImage: Int? = null,
    centerCrop: Boolean = true, fitCenter: Boolean = false
) {

    val imageUrl = url ?: ""

    val options = RequestOptions().dontTransform()

    if (placeholder != null) {
        options.placeholder(placeholder)
    } else {
        options.placeholder(ColorDrawable(Color.GRAY))
    }
    if (errorImage != null) {
        options.placeholder(errorImage)
    } else {
        options.placeholder(ColorDrawable(Color.GRAY))
    }
    if (fitCenter) {
        options.fitCenter()
    }
    if (centerCrop) {
        options.centerCrop()
    }

    Glide.with(this.context)
        .applyDefaultRequestOptions(options)
        .load(imageUrl)
        .into(this)
}