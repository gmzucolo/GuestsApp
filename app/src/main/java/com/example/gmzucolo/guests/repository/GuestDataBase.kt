package com.example.gmzucolo.guests.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gmzucolo.guests.model.GuestModel

//classe que fornece conexão com o banco de dados
//class GuestDataBase(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {
//
//    companion object {
//        private const val NAME = "guestdb"
//        private const val VERSION = 1
//    }
//
//    override fun onCreate(db: SQLiteDatabase) {
//        //cria o banco de dados com o nome Guest e três colunas
//        db.execSQL(
//            "CREATE TABLE " + DataBaseConstants.GUEST.TABLE_NAME + " (" +
//                    DataBaseConstants.GUEST.COLUMNS.ID + " integer primary key autoincrement, " +
//                    DataBaseConstants.GUEST.COLUMNS.NAME + " text, " +
//                    DataBaseConstants.GUEST.COLUMNS.PRESENCE + " integer);"
//        )
//    }
//
//    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//
//    }
//}
@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDataBase : RoomDatabase() {

    abstract fun guestDAO(): GuestDAO

    companion object {
        private lateinit var INSTANCE: GuestDataBase

        fun getDataBase(context: Context): GuestDataBase {
            if (!::INSTANCE.isInitialized) {
                //segurança quanto a thread que vai fazer a requisição
                synchronized(GuestDataBase::class) {
                    //builder do database
                    INSTANCE =
                        Room.databaseBuilder(context, GuestDataBase::class.java, "guestdb")
                            .addMigrations(MIGRATION_1_2)
                            //metodo que faz com que o room rode numa thread secundária
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }
        //metodo que faz o update do banco de dados, parametros com a antiga e nova versão
        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DELETE FROM Guest")
            }

        }
    }
}