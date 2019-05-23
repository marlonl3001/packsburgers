package br.com.mdr.packsburgers.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import br.com.mdr.packsburgers.PacksApp
import br.com.mdr.packsburgers.R
import br.com.mdr.packsburgers.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        NavigationUI.setupActionBarWithNavController(this, findNavController(R.id.nav_fragment))
        PacksApp.activity = this

        Navigation.findNavController(this, R.id.nav_fragment).navigate(R.id.loginFragment)
        supportActionBar?.hide()
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_fragment).navigateUp()
}
