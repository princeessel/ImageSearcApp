package com.example.imagesearcapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoUrls (
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String,
): Parcelable
