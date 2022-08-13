package com.example.gmzucolo.guests.view.form

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.gmzucolo.guests.R
import com.example.gmzucolo.guests.constants.DataBaseConstants
import com.example.gmzucolo.guests.databinding.ActivityGuestFormBinding
import com.example.gmzucolo.guests.model.GuestModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var guestViewModel: GuestFormViewModel

    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        guestViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        binding()
        setListener()
        observe()
        loadData()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.bt_save) {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked
            // metodo que constroi o objeto Guest
            val guest = GuestModel(guestId, name, presence)
            // metodo que envia para a viewmodel o objeto Guest
            guestViewModel.save(guest)
        }
    }

    private fun binding() {
        binding.btSave.setOnClickListener(this)
        binding.radioPresent.isChecked = true
    }

    private fun observe() {
        guestViewModel.guestModel.observe(this) {
            binding.editName.setText(it.name)
            if (it.presence) {
                binding.radioPresent.isChecked = true
            } else {
                binding.radioAbsent.isChecked = true
            }
        }
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
            guestViewModel.get(guestId)
        }
    }

    private fun setListener() {}
}