package com.example.convidadosapp.view.viewholder

import android.content.DialogInterface
import android.view.View
import androidx.appcompat.app.AlertDialog
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

        bind.textName.setOnLongClickListener {

            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de convidado")
                .setMessage("Tem certeza que deseja remover?")
                .setPositiveButton("Sim", object : DialogInterface.OnClickListener  {//Classe anônima
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        listener.onDelete(guest.id)
                    }

                })
                .setNegativeButton("Não", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.dismiss()//Se a dialog for diferente de null será dispersa
                    }

                })
                .create()
                .show()

            true
        }
    }

}