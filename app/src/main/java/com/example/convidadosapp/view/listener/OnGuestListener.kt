package com.example.convidadosapp.view.listener

interface OnGuestListener { //Interface que responderá pelo click dos itens da lista
    fun onClick(id: Int)
    fun onDelete(id: Int)
}