package br.com.cofermeta.toolbox

import android.app.Application
import br.com.cofermeta.toolbox.data.model.Jsession

val jsession = Jsession()

//@HiltAndroidApp
class ToolboxApplication: Application() {
    override fun onCreate() {
        super.onCreate()

    }
}