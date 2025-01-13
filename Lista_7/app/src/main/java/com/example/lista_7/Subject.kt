package com.example.lista_6_2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Subject(
    var name: String = ""
) : Parcelable
