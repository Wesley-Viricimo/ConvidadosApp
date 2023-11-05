package com.example.convidadosapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidadosapp.R
import com.example.convidadosapp.constants.DatabaseConstants
import com.example.convidadosapp.databinding.ActivityGuestFormBinding
import com.example.convidadosapp.model.GuestModel
import com.example.convidadosapp.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel
    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java) //Atrelando a view model a activity, quando a activity for destruída a view model também será

        binding.buttonSave.setOnClickListener(this)
        binding.radioPresent.isChecked = true

        observe() //Observe precisa estar pronto quando o load data chegar, que neste caso é a busca do convidado por id
        loadData()
    }

    override fun onClick(view: View) {
        if(view.id == R.id.button_save) {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked

            val model = GuestModel(guestId, name, presence) //Se for a criação de convidado o guestId será 0

            viewModel.save(model)
        }
    }

    private fun observe() {
        viewModel.guest.observe(this, Observer {
            binding.editName.setText(it.name)
            if(it.presence) {
                binding.radioPresent.isChecked = true
            } else {
                binding.radioAbsent.isChecked = true
            }
        })

        viewModel.saveGuest.observe(this, Observer{
            if (it != "") {
                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
                finish()
            } else {
                if (guestId == 0) {
                    Toast.makeText(applicationContext, "Houve falha na inserção", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "Houve falha na atualização", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            guestId = bundle.getInt(DatabaseConstants.GUEST.ID)
            viewModel.get(guestId) //Se for uma atualização de usuário o guestId será o valor recebido através do bundle
        }
    }
}