package br.com.mdr.packsburgers.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController

class LoginViewModel : ViewModel() {

    fun loginClickListener(): View.OnClickListener {
        return View.OnClickListener {
            it.findNavController().navigateUp()
        }
    }
}
