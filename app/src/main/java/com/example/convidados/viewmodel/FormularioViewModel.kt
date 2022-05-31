package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.service.model.ConvidadoModel
import com.example.convidados.service.repository.ConvidadoRepository

class FormularioViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val convidadoRepository = ConvidadoRepository.getInstance(mContext)

    private var _salvarConvidado = MutableLiveData<Boolean>()
    val salvarConvidado: LiveData<Boolean> = _salvarConvidado!!

    fun salvar(nome: String, presenca: Boolean) {
        val convidadoNovo = ConvidadoModel(nome = nome, presenca = presenca)
        convidadoRepository.salvar(convidadoNovo)
    }

}