package com.dicoding.myapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Member(
    val name: String,
    val birthdate: String,
    val description: String,
    val emoji: String,
    val photo: String
): Parcelable
