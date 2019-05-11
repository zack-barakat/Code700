package com.android.code700.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommonResponseModel(var errorCode: String? = null, var message: String? = null, var status: Int) : Parcelable