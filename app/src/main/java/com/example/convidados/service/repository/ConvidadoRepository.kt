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

    companion object {
        private lateinit var repository: ConvidadoRepository

        fun getInstance(context: Context): ConvidadoRepository {
            if (!::repository.isInitialized) {
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

        val convidado: ConvidadoModel? = null

        return try {
            val db = ConvidadoDataBaseHelper.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.CONVIDADO.COLUMNS.ID,
                DataBaseConstants.CONVIDADO.COLUMNS.NOME,
                DataBaseConstants.CONVIDADO.COLUMNS.PRESENCA
            )

            val cursor = db.query(
                DataBaseConstants.CONVIDADO.TABLE_NOME,
                projection,
                null,
                null,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUMNS.ID))
                    val nome =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUMNS.NOME))
                    val presenca =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUMNS.PRESENCA)) == 1)

                    val convidado = ConvidadoModel(id, nome, presenca)
                    list.add(convidado)
                }

            }

            cursor?.close()

            list
        } catch (e: Exception) {
            Log.e(TAG, "salvar: ", e)
            list
        }
    }

    fun buscaPresentes(): List<ConvidadoModel> {
        val list: MutableList<ConvidadoModel> = ArrayList()

        val convidado: ConvidadoModel? = null

        return try {
            val db = ConvidadoDataBaseHelper.readableDatabase

            val cursor = db.rawQuery("SELECT * FROM Convidado WHERE presenca = 1", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUMNS.ID))
                    val nome =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUMNS.NOME))
                    val presenca =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUMNS.PRESENCA)) == 1)

                    val convidado = ConvidadoModel(id, nome, presenca)
                    list.add(convidado)
                }

            }

            cursor?.close()

            list
        } catch (e: Exception) {
            Log.e(TAG, "salvar: ", e)
            list
        }
    }

    fun buscaAusentes(): List<ConvidadoModel> {
        val list: MutableList<ConvidadoModel> = ArrayList()
        return try {
            val db = ConvidadoDataBaseHelper.readableDatabase

            val cursor = db.rawQuery("SELECT id, nome, presenca FROM Convidado WHERE presenca = 0", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUMNS.NOME))
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUMNS.PRESENCA)) == 1)

                    val guest = ConvidadoModel(id, name, presence)
                    list.add(guest)
                }
            }

            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }
    }

    fun get(id: Int): ConvidadoModel? {

        var convidado: ConvidadoModel? = null
        return try {
            val db = ConvidadoDataBaseHelper.readableDatabase

            // Colunas que serão retornadas
            val projection = arrayOf(
                DataBaseConstants.CONVIDADO.COLUMNS.NOME,
                DataBaseConstants.CONVIDADO.COLUMNS.PRESENCA
            )

            // Filtro
            val selection = DataBaseConstants.CONVIDADO.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            val cursor = db.query(
                DataBaseConstants.CONVIDADO.TABLE_NOME,
                projection,
                selection,
                args,
                null,
                null,
                null
            )

            // Verifica se existem dados no cursor
            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()

                val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUMNS.NOME))
                val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUMNS.PRESENCA)) == 1)

                convidado = ConvidadoModel(id, name, presence)
            }

            cursor?.close()
            convidado
        } catch (e: Exception) {
            convidado
        }
    }

    // UPDATE
    fun update(convidado: ConvidadoModel): Boolean {
        return try {
            val db = ConvidadoDataBaseHelper.writableDatabase

            val contentValues = ContentValues()
            contentValues.put(DataBaseConstants.CONVIDADO.COLUMNS.NOME, convidado.nome)
            contentValues.put(DataBaseConstants.CONVIDADO.COLUMNS.PRESENCA, convidado.presenca)

            // Critério de seleção
            val selection = DataBaseConstants.CONVIDADO.COLUMNS.ID + " = ?"
            val args = arrayOf(convidado.id.toString())

            db.update(DataBaseConstants.CONVIDADO.TABLE_NOME, contentValues, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    // DELETE
    fun delete(id: Int): Boolean {
        return try {
            val db = ConvidadoDataBaseHelper.writableDatabase
            val selection = DataBaseConstants.CONVIDADO.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.CONVIDADO.TABLE_NOME, selection, args)

            true
        } catch (e: Exception) {
            false
        }
    }

}