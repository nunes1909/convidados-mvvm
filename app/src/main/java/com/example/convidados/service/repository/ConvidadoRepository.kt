package com.example.convidados.service.repository

import android.content.Context
import com.example.convidados.service.model.ConvidadoModel

class ConvidadoRepository private constructor(context: Context) {

    private var ConvidadoDataBaseHelper: ConvidadoDataBaseHelper =
        ConvidadoDataBaseHelper(context = context)

    companion object{
        private lateinit var repository: ConvidadoRepository

        fun getInstance(context: Context) : ConvidadoRepository{
            if (!::repository.isInitialized){
                repository = ConvidadoRepository(context = context)
            }
            return repository
        }
    }

    // SAVE
    fun salvar(convidado: ConvidadoModel) {

    }

    //READ

    fun buscaTodos(): List<ConvidadoModel> {
        val list: MutableList<ConvidadoModel> = ArrayList()
        return list
    }

    fun buscaPresentes(): List<ConvidadoModel> {
        val list: MutableList<ConvidadoModel> = ArrayList()
        return list
    }

    fun buscaAusentes(): List<ConvidadoModel> {
        val list: MutableList<ConvidadoModel> = ArrayList()
        return list
    }

    // UPDATE
    fun alterar(convidado: ConvidadoModel) {

    }

    // DELETE
    fun remover(convidado: ConvidadoModel) {

    }

}