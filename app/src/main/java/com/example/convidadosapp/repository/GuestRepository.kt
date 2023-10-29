package com.example.convidadosapp.repository

import android.content.Context

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

    fun save() {

    }

    fun update() {

    }
}