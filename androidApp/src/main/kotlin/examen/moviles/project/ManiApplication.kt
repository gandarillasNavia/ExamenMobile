package examen.moviles.project

import android.app.Application
import examen.moviles.project.di.initKoinAndroid
class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoinAndroid(this)
    }
}
