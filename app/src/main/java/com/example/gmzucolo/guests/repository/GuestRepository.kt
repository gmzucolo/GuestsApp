package com.example.gmzucolo.guests.repository

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.example.gmzucolo.guests.constants.DataBaseConstants
import com.example.gmzucolo.guests.model.GuestModel

//classe que fornece manipulação dos dados
class GuestRepository private constructor(context: Context) {

    private val guestDataBase = GuestDataBase(context)

    /**
     * Singleton: objeto que controla o número de instâncias da classe
     * Private constructor: construtor que não permite que ninguém possa instanciar a classe
     * **/
    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!Companion::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }

    }

    //metodo que faz a inserçao do objeto Guest na tabela, retorna verdadeiro ou falso
    fun insert(guest: GuestModel): Boolean {
        return try {
            //metodo que grava dados no banco de dados
            val db = guestDataBase.writableDatabase

            //metodo que transforma o parametro booleano presence em inteiro
            val presence = if (guest.presence) 1 else 0

            //metodo que passa os valores para a tabela
            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)

            true
        } catch (e: Exception) {
            false
        }
    }

    //metodo que faz a atualização do objeto Guest na tabela, retorna verdadeiro ou falso
    fun update(guest: GuestModel): Boolean {
        return try {
            //metodo que grava dados no banco de dados
            val db = guestDataBase.writableDatabase
            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.GUEST.TABLE_NAME, values, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    //metodo que faz a remoção do objeto Guest na tabela, retorna verdadeiro ou falso
    fun delete(guestId: Int): Boolean {
        return try {
            //metodo que grava dados no banco de dados
            val db = guestDataBase.writableDatabase

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(guestId.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    //metodo que retorna a lista de convidados
    @SuppressLint("Range")
    fun getAll(): List<GuestModel> {

        val list = mutableListOf<GuestModel>()

        try {
            //metodo que lê dados no banco de dados
            val db = guestDataBase.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE,
            )

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME, projection, null, null, null, null, null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))

                    //monta o objeto Guest e o adiciona a lista
                    list.add(GuestModel(id, name, presence == 1))
                }
            }

            //fechar o cursor
            cursor.close()
        } catch (e: Exception) {
            return list
        }
        return list
    }

    //metodo que retorna a lista de convidados
    @SuppressLint("Range")
    fun getOne(guestId: Int): GuestModel? {

        var guest: GuestModel? = null

        try {
            //metodo que lê dados no banco de dados
            val db = guestDataBase.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE,
            )

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            var args = arrayOf(guestId.toString())

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME, projection, selection, args, null, null, null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))

                    //monta o objeto Guest e o adiciona a lista
                    guest = (GuestModel(guestId, name, presence == 1))
                }
            }

            //fechar o cursor
            cursor.close()
        } catch (e: Exception) {
            return guest
        }
        return guest
    }

    //metodo que retorna a lista de convidados presentes
    @SuppressLint("Range")
    fun getAllPresents(): List<GuestModel> {

        val list = mutableListOf<GuestModel>()

        try {
            //metodo que lê dados no banco de dados
            val db = guestDataBase.readableDatabase

//            val projection = arrayOf(
//                DataBaseConstants.GUEST.COLUMNS.ID,
//                DataBaseConstants.GUEST.COLUMNS.NAME,
//                DataBaseConstants.GUEST.COLUMNS.PRESENCE,
//            )
//
//            //metodo que faz define uma seleção onde presença = 1 (presentes)
//            val selection = DataBaseConstants.GUEST.COLUMNS.PRESENCE + " = ?"
//            val args = arrayOf("1")
//
//            //metodo que faz a consulta na tabela name com a seleção acima definida
//            val cursor = db.query(
//                DataBaseConstants.GUEST.TABLE_NAME, projection, selection, null, null, null, null
//            )
//            O método acima comentado, pode ser substítuido pelo código abaixo (menos seguro)
            val cursor =
                db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 1", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))

                    //monta o objeto Guest e o adiciona a lista
                    list.add(GuestModel(id, name, presence == 1))
                }
            }

            //fechar o cursor
            cursor.close()
        } catch (e: Exception) {
            return list
        }
        return list
    }

    //metodo que retorna a lista de convidados ausentes
    @SuppressLint("Range")
    fun getAllAbsents(): List<GuestModel> {

        val list = mutableListOf<GuestModel>()

        try {
            //metodo que lê dados no banco de dados
            val db = guestDataBase.readableDatabase

            val cursor =
                db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 0", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))

                    //monta o objeto Guest e o adiciona a lista
                    list.add(GuestModel(id, name, presence == 1))
                }
            }

            //fechar o cursor
            cursor.close()
        } catch (e: Exception) {
            return list
        }
        return list
    }
}