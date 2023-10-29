package com.example.convidadosapp.repository

import android.content.ContentValues
import android.content.Context
import com.example.convidadosapp.model.GuestModel

class GuestRepository private constructor(context: Context) { //Construtor fechado, a classe não poderá ser instânciada

    private val guestDataBase = GuestDatabase(context) //Quando a classe GuestRepository for instânciada esta instância também será

    companion object {

        // Singleton - Conceito de singleton é basicamente controlar o número de instâncias de uma classe
        private lateinit var repository : GuestRepository

        fun getInstance(context: Context) : GuestRepository {
            if (!Companion::repository.isInitialized) { //verifica se o repositório está inicializado, caso não estiver irá inicializar -> Singleton para controlar intância da classe
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun insert(guest : GuestModel) {
        val db = guestDataBase.writableDatabase

        val presence = if (guest.presence) 1 else 0

        val values = ContentValues()
        values.put("name", guest.name)
        values.put("presence", presence)

        db.insert("Guest", null, values)
    }

    fun update() {

    }
}