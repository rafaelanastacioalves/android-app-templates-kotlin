package com.example.rafaelanastacioalves.moby.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.rafaelanastacioalves.moby.repository.database.AppDataBase


class MainApplication : Application() {
    override fun onCreate() {
        setupDB()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        super.onCreate()
    }

    private fun setupDB() {
        AppDataBase.setupAtApplicationStartup(this)
    }

    /**
     * A tree which logs important information for crash reporting.
     */

}
