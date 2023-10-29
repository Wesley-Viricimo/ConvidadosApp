package com.example.convidadosapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.convidadosapp.repository.GuestRepository

class GuestFormViewModel : ViewModel() {

    private val repository = GuestRepository.getInstance()


}