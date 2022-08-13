package com.example.gmzucolo.guests.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.gmzucolo.guests.databinding.ItemGuestBinding
import com.example.gmzucolo.guests.model.GuestModel

class GuestViewHolder(private val binding: ItemGuestBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun binding(guestModel: GuestModel) {
        binding.textName.text = guestModel.name
    }

}