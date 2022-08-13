package com.example.gmzucolo.guests.repository

class GuestRepository private constructor() {

    /**
     * Singleton: objeto que controla o número de instâncias da classe
     * Private constructor: construtor que não permite que ninguém possa instanciar a classe
     * **/
    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(): GuestRepository {
            if (!Companion::repository.isInitialized) {
                repository = GuestRepository()
            }
            return repository
        }

        fun save() {

        }
    }
}