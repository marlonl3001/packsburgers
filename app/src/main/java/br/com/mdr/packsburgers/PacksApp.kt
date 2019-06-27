package br.com.mdr.packsburgers

import android.app.Application
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
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

    fun setActionBar(toolbar: Toolbar, setVisible: Boolean) {
        activity.setSupportActionBar(toolbar)
        if (!setVisible)
            activity.supportActionBar?.hide()
        else {
            activity.supportActionBar?.show()
            toolbar.setTitleTextColor(ContextCompat.getColor(activity, R.color.transparent))
        }
    }
}