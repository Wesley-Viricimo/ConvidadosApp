package com.example.convidadosapp.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.convidadosapp.databinding.RowGuestBinding
import com.example.convidadosapp.model.GuestModel
import com.example.convidadosapp.view.listener.OnGuestListener

class GuestViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) : RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel) {
        bind.textName.text = guest.name

        bind.textName.setOnClickListener {
            listener.onClick(guest.id) //Quando algum item da lista de convidados for clicado será chamado o método onclick da fragment
        }
    }

}