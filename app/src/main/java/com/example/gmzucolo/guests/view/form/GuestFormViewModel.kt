package com.example.gmzucolo.guests.view.form

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gmzucolo.guests.model.GuestModel
import com.example.gmzucolo.guests.repository.GuestRepository

// diferença entre ViewModel e AndroidViewModel é que este possui um contexto
class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application)

    private val _guestModel = MutableLiveData<GuestModel>()
    val guestModel: LiveData<GuestModel> = _guestModel

    fun save(guest: GuestModel) {
        if (guest.id == 0) {
            //metodo que insere para o repositorio o objeto Guest
            repository.insert(guest)
        } else {
            //metodo que atualiza para o repositorio o objeto Guest
            repository.update(guest)
        }
    }

    fun get(guestId: Int) {
        _guestModel.value = repository.getOne(guestId)
    }
}