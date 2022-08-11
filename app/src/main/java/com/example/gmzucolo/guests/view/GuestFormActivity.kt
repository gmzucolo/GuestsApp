package com.example.gmzucolo.guests.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.gmzucolo.guests.viewmodel.GuestFormViewModel
import com.example.gmzucolo.guests.R
import com.example.gmzucolo.guests.databinding.ActivityGuestFormBinding

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var guestViewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        guestViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        binding()
        setListener()
    }

    override fun onClick(view: View) {
        if(view.id == R.id.bt_save) {

        }
    }

    private fun binding() {
        binding.btSave.setOnClickListener(this)
        binding.radioPresent.isChecked = true
    }

    private fun setListener() {}
}