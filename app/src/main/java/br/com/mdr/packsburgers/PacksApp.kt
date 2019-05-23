package br.com.mdr.packsburgers

import android.app.Application
import br.com.mdr.packsburgers.view.ui.MainActivity

class PacksApp: Application() {

    companion object {
        lateinit var instance: PacksApp
        lateinit var activity: MainActivity
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}