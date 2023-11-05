package com.example.convidadosapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidadosapp.model.GuestModel
import com.example.convidadosapp.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) { //Android view model pode ser utilizado quando o contexto é necessário

    private val repository = GuestRepository(application)

    private val guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel

    private val _saveGuest = MutableLiveData<String>()
    val saveGuest: LiveData<String> = _saveGuest

    fun save(guest : GuestModel) {
        if (guest.id == 0) {
            if(repository.insert(guest)) {
                _saveGuest.value = "Inserção com sucesso!"//SaveGuest irá observar o retorno do método insert para validar se o convidado foi inserido com sucesso ou não
            } else {
                _saveGuest.value = ""
            }
        } else {
            if(repository.update(guest)) {
                _saveGuest.value = "Atualização com sucesso"//SaveGuest irá observar o retorno do método update para validar se o convidado foi atualizado com sucesso ou não
            } else {
                _saveGuest.value = ""
            }
        }
    }

    fun get(id: Int) {
        guestModel.value = repository.get(id)
    }


}