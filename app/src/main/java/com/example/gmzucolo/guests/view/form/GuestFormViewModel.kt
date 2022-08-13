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

    private val _saveGuest = MutableLiveData<String>()
    val saveGuest: LiveData<String> = _saveGuest

    fun save(guest: GuestModel) {
        if (guest.id == 0) {
            if (repository.insert(guest)) {
                //metodo que insere para o repositorio o objeto Guest
                _saveGuest.value = "Inserção com sucesso"
            } else {
                _saveGuest.value = "Falha"
            }
        } else {
            if (repository.update(guest)) {
                //metodo que atualiza para o repositorio o objeto Guest
                _saveGuest.value = "Atualização com sucesso"
            } else {
                _saveGuest.value = "Falha"
            }
        }
    }


    fun get(guestId: Int) {
        _guestModel.value = repository.getOne(guestId)
    }
}