package com.mad.dndbackpack

import android.app.Application
import com.mad.dndbackpack.data.AppContainer
import com.mad.dndbackpack.data.AppDataContainer

class DnDBackpackApplication : Application() {
    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
