package com.example.gmzucolo.guests.view.viewholder

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
            listener.onDelete(guestModel.id)
            true
        }
    }
}
