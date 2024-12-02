package com.example.lista2


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Exercise(var content : String = "", var points : Int = 0) : Parcelable