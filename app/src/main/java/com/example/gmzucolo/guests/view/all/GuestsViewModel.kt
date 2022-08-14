package com.example.gmzucolo.guests.view.all

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gmzucolo.guests.model.GuestModel
import com.example.gmzucolo.guests.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: GuestRepository =
        GuestRepository(application.applicationContext)
    private val listAllGuests = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listAllGuests

    fun getAll() {
        listAllGuests.value = repository.getAll()
    }

    fun getPresent() {
        listAllGuests.value = repository.getPresents()
    }

    fun getAbsent() {
        listAllGuests.value = repository.getAbsents()
    }

    fun delete(id: Int) {
        repository.delete(id)
    }
}