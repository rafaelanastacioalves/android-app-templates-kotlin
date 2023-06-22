package com.example.rafaelanastacioalves.moby.ui.entitymainlisting


import android.content.Intent
import android.os.Build
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityOptionsCompat
import com.example.rafaelanastacioalves.moby.R
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.ui.entitydetailing.EntityDetailsFragment

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}


