package br.com.mdr.packsburgers.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import br.com.mdr.packsburgers.model.Usuario

class UserDetailViewModel : ViewModel() {
    var user = MutableLiveData<Usuario>()

    fun getUser(): Usuario {
        return user.value!!
    }
}
