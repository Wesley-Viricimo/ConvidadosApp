package com.example.convidadosapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidadosapp.model.GuestModel
import com.example.convidadosapp.repository.GuestRepository

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GuestRepository = GuestRepository.getInstance(application.applicationContext)

    private val listAllGuests = MutableLiveData<List<GuestModel>>() //Preparando a variável para receber um lista mutável de convidados
    val guests: LiveData<List<GuestModel>> = listAllGuests //Recebendo a lista utilizando LiveData para ser utilizado o observável

    fun getAll() {
        listAllGuests.value = repository.getAll() //Atribuindo convidados a lista de convidados
    }

    fun delete(id: Int) {
        repository.delete(id)
    }
}