package com.example.convidados.service.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.convidados.service.constants.DataBaseConstants

class ConvidadoDataBaseHelper(context: Context) : SQLiteOpenHelper(context, NOME_DB, null, VERSAO_DB) {

    companion object{
        private const val VERSAO_DB = 1
        private const val NOME_DB = "convidado.db"

        private const val CREATE_TABLE_CONVIDADO =
            ("create table " + DataBaseConstants.CONVIDADO.TABLE_NOME + " ("
                    + DataBaseConstants.CONVIDADO.COLUMNS.ID + " integer primary key autoincrement, "
                    + DataBaseConstants.CONVIDADO.COLUMNS.NOME + " text, "
                    + DataBaseConstants.CONVIDADO.COLUMNS.PRESENCA + " integer);")
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_CONVIDADO)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}