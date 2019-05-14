package com.android.code700.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommonResponseModel(var error: Boolean, var message: String? = null) : Parcelable
