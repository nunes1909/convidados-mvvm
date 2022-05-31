package com.example.convidados.service.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConvidadoModel(
    val id: Int = 0,
    val nome: String,
    var presenca: Boolean
) : Parcelable