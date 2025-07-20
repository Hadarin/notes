package com.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonModel (
    val name: String,
    val age: Int,
    val latitude: Float,
    val amount: Double,
    val isEnabled: Boolean,
    val id: Long
) : Parcelable