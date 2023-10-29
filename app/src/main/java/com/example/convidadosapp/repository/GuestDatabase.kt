package com.example.convidadosapp.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class GuestDatabase(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {
        private const val NAME = "guestdb"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) { //Criação do banco - OnCreate é executado apenas uma vez no projeto
        db.execSQL("CREATE TABLE Guest(" +
        "id integer primary key autoincrement, " +
        "name text, " +
        "presence integer);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) { //Executa atualizações no banco de dados do usuário

    }

}