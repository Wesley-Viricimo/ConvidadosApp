package com.example.convidadosapp.repository

class GuestRepository private constructor() { //Construtor fechado, a classe não poderá ser instânciada

    companion object {

        // Singleton - Conceito de singleton é basicamente controlar o número de instâncias de uma classe
        private lateinit var repository : GuestRepository

        fun getInstance() : GuestRepository {
            if (!Companion::repository.isInitialized) { //verifica se o repositório está inicializado, caso não estiver irá inicializar -> Singleton para controlar intância da classe
                repository = GuestRepository()
            }
            return repository
        }
    }
}