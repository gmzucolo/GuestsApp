package com.example.gmzucolo.guests.viewmodel

import androidx.lifecycle.ViewModel
import com.example.gmzucolo.guests.repository.GuestRepository

class GuestFormViewModel : ViewModel() {

    private val repository = GuestRepository.getInstance()
}