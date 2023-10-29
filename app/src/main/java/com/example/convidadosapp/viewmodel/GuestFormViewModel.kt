package com.example.convidadosapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.convidadosapp.model.GuestModel
import com.example.convidadosapp.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) { //Android view model pode ser utilizado quando o contexto é necessário

    private val repository = GuestRepository.getInstance(application)

    fun insert(guest : GuestModel) {
        repository.insert(guest)
    }


}