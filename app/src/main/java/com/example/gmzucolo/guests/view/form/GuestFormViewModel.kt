package com.example.gmzucolo.guests.view.form

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gmzucolo.guests.model.GuestModel
import com.example.gmzucolo.guests.repository.GuestRepository

// diferença entre ViewModel e AndroidViewModel é que este possui um contexto
class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository(application.applicationContext)

    private var _guestModel = MutableLiveData<GuestModel>()
    val guestModel: LiveData<GuestModel> = _guestModel

    private val _saveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = _saveGuest

    fun save(id: Int, name: String, presence: Boolean) {
        // metodo que constroi o objeto Guest, metodo apply representa que nessa instância tais valores serão aplicados
        val guest = GuestModel().apply {
            this.id = id
            this.name = name
            this.presence = presence
        }

        if (id == 0) {
            //metodo que insere para o repositorio o objeto Guest
            _saveGuest.value = repository.insert(guest)
        } else {
            //metodo que atualiza para o repositorio o objeto Guest
            _saveGuest.value = repository.update(guest)
        }
    }


    fun get(id: Int) {
        _guestModel.value = repository.getOne(id)
    }
}