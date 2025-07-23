package com.example.nguyentronghieu

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val name: String,
    val description: String,
    val price: String,
    val imageUrl: String,
    val type: String
) : Parcelable