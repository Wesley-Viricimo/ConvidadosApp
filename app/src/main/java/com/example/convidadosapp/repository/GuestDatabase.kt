package com.example.convidadosapp.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.convidadosapp.model.GuestModel

@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDatabase : RoomDatabase() {

    abstract fun guestDAO(): GuestDAO

    companion object {
        // Singleton - Conceito de singleton é basicamente controlar o número de instâncias de uma classe
        private lateinit var INSTANCE: GuestDatabase
        fun getDatabase(context: Context): GuestDatabase {
            if (!::INSTANCE.isInitialized) {
                synchronized(GuestDatabase::class) { //Função evita que duas threads executem o mesmo trecho de código ao mesmo tempo
                    INSTANCE = Room.databaseBuilder(
                        context,
                        GuestDatabase::class.java,
                        "guestDB"
                    ) //Instância de Room.databasebuilder pede um contexto, a classe que será utilizada e o nome do banco de dados
                        .addMigrations(MIGRATION_1_2)
                        .allowMainThreadQueries() //Permitir consultas ao banco na thread principal
                        .build()
                }
            }
            return INSTANCE
        }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DELETE FROM Guest")
            }
        }

    }

}