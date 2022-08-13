package com.example.gmzucolo.guests.view.all

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gmzucolo.guests.model.GuestModel
import com.example.gmzucolo.guests.repository.GuestRepository

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: GuestRepository =
        GuestRepository.getInstance(application.applicationContext)
    private val listAllGuestes = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listAllGuestes

    fun getAll() {
        listAllGuestes.value = repository.getAll()
    }
}