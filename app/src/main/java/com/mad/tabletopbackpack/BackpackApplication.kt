package com.mad.tabletopbackpack

import android.app.Application
import com.mad.tabletopbackpack.data.AppContainer
import com.mad.tabletopbackpack.data.AppDataContainer

class BackpackApplication : Application() {
    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
