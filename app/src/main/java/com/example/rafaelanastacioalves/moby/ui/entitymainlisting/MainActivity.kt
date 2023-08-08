package com.example.rafaelanastacioalves.moby.ui.entitymainlisting


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rafaelanastacioalves.moby.R
import com.example.rafaelanastacioalves.moby.databinding.ActivityMainBinding
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.listeners.RecyclerViewClickListener
import com.example.rafaelanastacioalves.moby.ui.entitydetailing.EntityDetailActivity
import com.example.rafaelanastacioalves.moby.ui.entitydetailing.EntityDetailsFragment
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), RecyclerViewClickListener{

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater)
    }
    private val mClickListener = this
    private val mainEntityAdapter: MainEntityAdapter by lazy {
        MainEntityAdapter(this)
    }
    private val mRecyclerView: RecyclerView? by lazy {
        binding.mainEntityList
    }
    private val mainScreenViewModel: MainScreenViewModel by lazy {
        ViewModelProvider(this).get(MainScreenViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
        setupRecyclerView()
        loadData()
        subscribe()

    }

    private fun loadData() {
        mainScreenViewModel.loadDataIfNecessary()
    }

    /**
     * Here we call a State Flow from viewModel in a secure manner so
     * we are sure we're not wasting resources when activity is destroyed, etc
     * As explained in [this link](https://medium.com/androiddevelopers/migrating-from-livedata-to-kotlins-flow-379292f419fb)
     */
    private fun subscribe() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(
                state = Lifecycle.State.STARTED
            ){
                mainScreenViewModel.mainEntityListFlowState.collect{ mainEntities ->
                    Log.d(this.javaClass.simpleName, "collecting entities")
                    populateRecyclerView(mainEntities)
                }
            }
        }
    }

    private fun setupViews() {
        setContentView(binding.root)

    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(applicationContext)
        mRecyclerView!!.layoutManager = layoutManager
        mainEntityAdapter.setRecyclerViewClickListener(mClickListener)
        mRecyclerView!!.adapter = mainEntityAdapter
    }


    private fun populateRecyclerView(list: Resource<List<MainEntity>>?) {
        if (list == null) {
            mainEntityAdapter.setItems(null)

        } else if (list.data!=null) {
            mainEntityAdapter.setItems(list.data)
        }

    }


    override fun onClick(view: View, position: Int) {
        val MainEntity = mainEntityAdapter.getItems()!!.get(position)

        val transitionImageView = view.findViewById<View>(R.id.main_entity_imageview)
        startActivityByVersion(MainEntity, transitionImageView as AppCompatImageView)


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
