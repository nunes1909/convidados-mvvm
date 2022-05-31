package com.example.convidados.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidados.service.model.ConvidadoModel
import com.example.convidados.service.repository.ConvidadoRepository

class FormularioViewModel(context: Context) : ViewModel() {

    private val convidadoRepository = ConvidadoRepository.getInstance(context)

    private var _salvarConvidado = MutableLiveData<Boolean>()
    val salvarConvidado: LiveData<Boolean> = _salvarConvidado!!

    fun salvar(nome: String, presenca: Boolean) {
        val convidadoNovo = ConvidadoModel(nome = nome, presenca = presenca)
        convidadoRepository.salvar(convidadoNovo)
    }

}