package com.example.gmzucolo.guests.repository

import android.content.ContentValues
import android.content.Context
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

    fun insert(guest: GuestModel) {

        //metodo que grava dados no banco de dados
        val db = guestDataBase.writableDatabase

        //metodo que transforma o parametro booleano presence em inteiro
        val presence = if (guest.presence) 1 else 0

        //metodo que passa os valores para a tabela
        val values = ContentValues()
        values.put("name", guest.name)
        values.put("presence", presence)

        db.insert("Guest", null, values)
    }
}