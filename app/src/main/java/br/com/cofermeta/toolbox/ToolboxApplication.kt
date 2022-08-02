package br.com.cofermeta.toolbox

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ToolboxApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}