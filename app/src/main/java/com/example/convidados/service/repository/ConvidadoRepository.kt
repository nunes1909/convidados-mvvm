package com.example.convidados.service.repository

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.convidados.service.constants.DataBaseConstants
import com.example.convidados.service.model.ConvidadoModel

private const val TAG = "ConvidadoRepository"

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
    fun salvar(convidado: ConvidadoModel): Boolean {
        return try {
            val db = ConvidadoDataBaseHelper.writableDatabase

            val value = ContentValues()
            value.put(DataBaseConstants.CONVIDADO.COLUMNS.NOME, convidado.nome)
            value.put(DataBaseConstants.CONVIDADO.COLUMNS.PRESENCA, convidado.presenca)

            db.insert(DataBaseConstants.CONVIDADO.TABLE_NOME, null, value)

            true
        } catch (e: Exception) {
            Log.e(TAG, "salvar: ", e)
            false
        }
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