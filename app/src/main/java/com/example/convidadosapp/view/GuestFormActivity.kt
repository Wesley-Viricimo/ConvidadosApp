package com.example.convidadosapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.convidadosapp.R
import com.example.convidadosapp.constants.DatabaseConstants
import com.example.convidadosapp.databinding.ActivityGuestFormBinding
import com.example.convidadosapp.model.GuestModel
import com.example.convidadosapp.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java) //Atrelando a view model a activity, quando a activity for destruída a view model também será

        binding.buttonSave.setOnClickListener(this)
        binding.radioPresent.isChecked = true

        loadData()
    }

    override fun onClick(view: View) {
        if(view.id == R.id.button_save) {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked

            val model = GuestModel(0, name, presence)

            viewModel.insert(model)
        }
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            val guestId = bundle.getInt(DatabaseConstants.GUEST.ID)
            viewModel.get(guestId)
        }
    }
}