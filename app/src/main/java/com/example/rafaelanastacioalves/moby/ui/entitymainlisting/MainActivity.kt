package com.example.rafaelanastacioalves.moby.ui.entitymainlisting


import MainScreen
import android.content.Intent
import android.os.Build
import android.os.Bundle

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rafaelanastacioalves.moby.R
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.ui.entitydetailing.EntityDetailActivity
import com.example.rafaelanastacioalves.moby.ui.entitydetailing.EntityDetailsFragment
import com.example.rafaelanastacioalves.moby.listeners.RecyclerViewClickListener
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.example.rafaelanastacioalves.moby.application.MainApplication
import com.example.rafaelanastacioalves.moby.domain.interactors.Interactor
import com.example.rafaelanastacioalves.moby.domain.interactors.MainEntityListInteractor
import com.example.rafaelanastacioalves.moby.ui.theme.ProjectTheme

class MainActivity : AppCompatActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }


    private fun startActivityByVersion(mainEntity: MainEntity, transitionImageView: AppCompatImageView) {
        val i = Intent(this, EntityDetailActivity::class.java)
        i.putExtra(EntityDetailsFragment.ARG_ENTITY_ID, mainEntity.id)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            var bundle: Bundle? = null
            bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity,
                    transitionImageView, transitionImageView.transitionName).toBundle()
            startActivity(i, bundle)

        } else {
            startActivity(i)
        }
    }
}


