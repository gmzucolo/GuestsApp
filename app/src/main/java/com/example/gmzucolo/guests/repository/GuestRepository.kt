package com.example.gmzucolo.guests.repository

import android.content.ContentValues
import android.content.Context
import com.example.gmzucolo.guests.constants.DataBaseConstants
import com.example.gmzucolo.guests.model.GuestModel

// classe que fornece manipulação dos dados
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

    fun update(guest: GuestModel): Boolean {
        return try {
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
}