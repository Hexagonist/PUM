package com.example.lista_7

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    var indexNum: String = "",
    var name: String = "",
    var surname: String = "",
    var mean: String = "",
    var yearOfStudy: String = ""
    // Stworz indeks studenta zeby poda
) : Parcelable
