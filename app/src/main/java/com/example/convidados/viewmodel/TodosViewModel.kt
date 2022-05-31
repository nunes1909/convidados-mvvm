package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.service.model.ConvidadoModel
import com.example.convidados.service.repository.ConvidadoRepository

class TodosViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ConvidadoRepository.getInstance(application.applicationContext)

    private var _todosConvidados = MutableLiveData<List<ConvidadoModel>>()
    val todosConvidados: LiveData<List<ConvidadoModel>> = _todosConvidados

    fun carregaLista() {
        _todosConvidados.value = repository.buscaTodos()
    }

    fun delete(convidadoModel: ConvidadoModel) {
        repository.delete(convidadoModel.id)
    }

}