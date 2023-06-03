package com.example.rafaelanastacioalves.moby.ui.entitymainlisting


import android.content.Intent
import android.os.Build
import android.os.Bundle

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rafaelanastacioalves.moby.R
import com.example.rafaelanastacioalves.moby.binding.ActivityDataBindingComponent
import com.example.rafaelanastacioalves.moby.databinding.ActivityMainBinding
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.listeners.DataBoundClickListener
import com.example.rafaelanastacioalves.moby.ui.entitydetailing.EntityDetailActivity
import com.example.rafaelanastacioalves.moby.ui.entitydetailing.EntityDetailsFragment
import com.example.rafaelanastacioalves.moby.listeners.RecyclerViewClickListener

class MainActivity : AppCompatActivity(), DataBoundClickListener<MainEntity>{

    lateinit var binding: ActivityMainBinding
    private val dataBindingComponent: DataBindingComponent =  ActivityDataBindingComponent(this)
    private val mClickListener = this
    private var mainEntityAdapter: MainEntityAdapter? = null
    private val mLiveDataMainEntityListViewModel: LiveDataMainEntityListViewModel by lazy {
        ViewModelProvider(this).get(LiveDataMainEntityListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
        setupRecyclerView()
        subscribe()
        loadData()

    }

    private fun loadData() {
        mLiveDataMainEntityListViewModel.loadDataIfNecessary()
    }


    private fun subscribe() {
        binding.mainEntityListResult =  mLiveDataMainEntityListViewModel.mainEntityListLiveData
        mLiveDataMainEntityListViewModel.mainEntityListLiveData.observeForever(Observer { mainEntities ->
            populateRecyclerView(mainEntities)
        })
    }

    private fun setupViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.lifecycleOwner = this
        binding.viewmodel = mLiveDataMainEntityListViewModel

    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(applicationContext)
        binding.mainEntityList.layoutManager = layoutManager
        if (mainEntityAdapter == null) {
            mainEntityAdapter = MainEntityAdapter(
                this,
            dataBindingComponent)
        }
        mainEntityAdapter!!.setRecyclerViewClickListener(mClickListener)
        binding.mainEntityList.adapter = mainEntityAdapter
    }


    private fun populateRecyclerView(list: Resource<List<MainEntity>>?) {
        if (list == null) {
            mainEntityAdapter?.submitList(null)

        } else if (list.data!=null) {
            mainEntityAdapter?.submitList(list.data)
        }

    }


    override fun onClick(view: View, item: MainEntity) {
        val transitionImageView = view.findViewById<View>(R.id.main_entity_imageview)
        startActivityByVersion(item, transitionImageView as AppCompatImageView)
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
