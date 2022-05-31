package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.service.constants.AUSENTES
import com.example.convidados.service.constants.PRESENTES
import com.example.convidados.service.constants.TODOS
import com.example.convidados.service.model.ConvidadoModel
import com.example.convidados.service.repository.ConvidadoRepository

class ConvidadosViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ConvidadoRepository.getInstance(application.applicationContext)

    private var _todosConvidados = MutableLiveData<List<ConvidadoModel>>()
    val todosConvidados: LiveData<List<ConvidadoModel>> = _todosConvidados

    fun carregaLista(filtro: Int) {

        when(filtro) {
            TODOS -> _todosConvidados.value = repository.buscaTodos()
            PRESENTES -> _todosConvidados.value = repository.buscaPresentes()
            else -> _todosConvidados.value = repository.buscaAusentes()
        }

    }

    fun delete(convidadoModel: ConvidadoModel) {
        repository.delete(convidadoModel.id)
    }

}