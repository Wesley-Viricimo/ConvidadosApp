package com.example.convidadosapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.convidadosapp.databinding.RowGuestBinding
import com.example.convidadosapp.model.GuestModel
import com.example.convidadosapp.view.listener.OnGuestListener
import com.example.convidadosapp.view.viewholder.GuestViewHolder

class GuestsAdapter : RecyclerView.Adapter<GuestViewHolder>() { //Adapter é responsável por realizar a junção do layout com os dados
    private var guestList: List<GuestModel> = listOf() //Instância de uma lista de convidados vazia
    private lateinit var listener: OnGuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder { //Método responsável pela criação do layout, será chamado para cada item da lista
        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestViewHolder(item, listener)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) { //Responsável por atribuir os dados para o layout
        holder.bind(guestList[position])
    }

    override fun getItemCount(): Int { //Método responsável pelo tamanho da listagem
        return guestList.count()
    }

    fun updateGuests(list: List<GuestModel>) {
        guestList = list
        notifyDataSetChanged() //Método responsável por notificar a recycler view que a lista de dados foi atualizada
    }

    fun attachListener(guestListener: OnGuestListener) {
        listener = guestListener
    }
}