package com.example.gmzucolo.guests.constants

class DataBaseConstants private constructor() {

    object GUEST {
        const val TABLE_NAME = "Guest"
        const val ID = "id"

        object COLUMNS {
            const val ID = "id"
            const val NAME = "name"
            const val PRESENCE = "presence"
        }

    }
}