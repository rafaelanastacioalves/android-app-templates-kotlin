package com.example.rafaelanastacioalves.moby.entitymainlisting


import android.content.Intent
import android.os.Build
import android.os.Bundle

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rafaelanastacioalves.moby.R
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.entitydetailing.EntityDetailActivity
import com.example.rafaelanastacioalves.moby.entitydetailing.EntityDetailsFragment
import com.example.rafaelanastacioalves.moby.listeners.RecyclerViewClickListener
import timber.log.Timber

class MainActivity : AppCompatActivity(), RecyclerViewClickListener{

    private val mClickListener = this
    private var mainEntityAdapter: MainEntityAdapter? = null
    private var mRecyclerView: RecyclerView? = null
    lateinit private var mLiveDataMainEntityListViewModel: LiveDataMainEntityListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
        setupRecyclerView()
        subscribe()

    }


    private fun subscribe() {
        mLiveDataMainEntityListViewModel = ViewModelProvider.NewInstanceFactory().create(LiveDataMainEntityListViewModel::class.java)
        mLiveDataMainEntityListViewModel.loadData().observeForever(Observer { mainEntities ->
            Timber.d("On Changed")
            populateRecyclerView(mainEntities)
        })
    }

    private fun setupViews() {
        setContentView(R.layout.activity_main)
        Timber.tag("LifeCycles")
        Timber.i("onCreate Activity")
    }

    private fun setupRecyclerView() {
        mRecyclerView = findViewById<View>(R.id.main_entity_list) as RecyclerView
        val layoutManager = LinearLayoutManager(applicationContext)
        mRecyclerView!!.layoutManager = layoutManager
        if (mainEntityAdapter == null) {
            mainEntityAdapter = MainEntityAdapter(this)
        }
        mainEntityAdapter!!.setRecyclerViewClickListener(mClickListener)
        mRecyclerView!!.adapter = mainEntityAdapter
    }


    private fun populateRecyclerView(list: Resource<List<MainEntity>>?) {
        if (list == null) {
            mainEntityAdapter!!.setItems(null)
            //TODO add any error managing
            Timber.w("Nothing returned from Main Entity List API")

        } else if (list.data!=null) {
            mainEntityAdapter!!.setItems(list.data)
        }

    }


    override fun onClick(view: View, position: Int) {
        val MainEntity = mainEntityAdapter!!.getItems()!!.get(position)

        val transitionImageView = view.findViewById<View>(R.id.main_entity_imageview)
        startActivityByVersion(MainEntity, transitionImageView as AppCompatImageView)


    }

    private fun startActivityByVersion(mainEntity: MainEntity, transitionImageView: AppCompatImageView) {
        val i = Intent(this, EntityDetailActivity::class.java)
        i.putExtra(EntityDetailsFragment.ARG_ENTITY_ID, mainEntity.getId())

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
