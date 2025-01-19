package com.example.lista_8

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Subject(
    var name: String = "",
    var grade: Float = 0.0f
) : Parcelable
