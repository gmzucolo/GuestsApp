package com.example.gmzucolo.guests.view.viewholder

import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.gmzucolo.guests.databinding.ItemGuestBinding
import com.example.gmzucolo.guests.model.GuestModel
import com.example.gmzucolo.guests.view.listener.OnGuestListener

//associa as informações inseridas pelo usuário a recycler
class GuestViewHolder(
    private val binding: ItemGuestBinding,
    private val listener: OnGuestListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun binding(guestModel: GuestModel) {
        binding.textName.text = guestModel.name
        binding.textName.setOnClickListener {
            listener.onClick(guestModel.id)
        }
        binding.textName.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de convidado")
                .setMessage("Tem certeza que deseja remover?")
                .setPositiveButton("Sim") { dialog, which -> listener.onDelete(guestModel.id) }
                .setNegativeButton("Não", null)
                .create()
                .show()
            true
        }
    }
}
