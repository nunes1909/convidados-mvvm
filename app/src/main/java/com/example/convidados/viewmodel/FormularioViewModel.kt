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

    //salvar
    private var _salvarConvidado = MutableLiveData<Boolean>()
    val salvarConvidado: LiveData<Boolean> = _salvarConvidado!!

    fun salvar(id: Int, nome: String, presenca: Boolean) {
        val convidado = ConvidadoModel(id = id, nome = nome, presenca = presenca)

        if (id == 0) {
            _salvarConvidado.value = convidadoRepository.salvar(convidado)
        } else {
            _salvarConvidado.value = convidadoRepository.update(convidado)
        }

    }

    //visualização
    private var _carregaConvidado = MutableLiveData<ConvidadoModel>()
    val carregaConvidado: LiveData<ConvidadoModel> = _carregaConvidado

    fun load(it: Int) {
        _carregaConvidado.value = convidadoRepository.get(it)
    }


}