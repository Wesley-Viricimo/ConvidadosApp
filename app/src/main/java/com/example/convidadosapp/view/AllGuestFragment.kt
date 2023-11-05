package com.example.convidadosapp.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.convidadosapp.constants.DatabaseConstants
import com.example.convidadosapp.databinding.FragmentAllGuestsBinding
import com.example.convidadosapp.view.adapter.GuestsAdapter
import com.example.convidadosapp.view.listener.OnGuestListener
import com.example.convidadosapp.viewmodel.GuestsViewModel

class AllGuestFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GuestsViewModel
    private val adapter = GuestsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)
        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        //Layout da lista
        _binding!!.recyclerAllGuests.layoutManager = LinearLayoutManager(context)

        //Adapter
        binding.recyclerAllGuests.adapter = adapter

        val listener = object : OnGuestListener { //Classe anônima
            override fun onClick(id: Int) { //Recebendo o id da view holder
                val intent  = Intent(context, GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(DatabaseConstants.GUEST.ID, id)
                intent.putExtras(bundle) //Quando activity for inicializada, essas informações serão passadas para ela

                startActivity(intent)
            }

            override fun onDelete(id: Int) { //Recebendo o id da view holder
                viewModel.delete(id)
                viewModel.getAll() //Atualizar a lista de convidados após deletar
            }

        }

        adapter.attachListener(listener)

        viewModel.getAll()

        observe()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll() //Quando usuário sair da activity e posteriormente retornar para ela deve fazer uma busca novamente
    }

    private fun observe() {
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }
}