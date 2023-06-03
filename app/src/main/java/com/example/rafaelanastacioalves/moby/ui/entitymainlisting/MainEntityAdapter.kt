package com.example.rafaelanastacioalves.moby.ui.entitymainlisting;

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.example.rafaelanastacioalves.moby.R
import com.example.rafaelanastacioalves.moby.common.DataBoundListAdapter
import com.example.rafaelanastacioalves.moby.databinding.MainEntityItemBinding
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.listeners.DataBoundClickListener

class MainEntityAdapter(context: Context,
    val dataBindingComponent: DataBindingComponent
) :
    DataBoundListAdapter<MainEntity, MainEntityItemBinding>(
        diffCallback = object : DiffUtil.ItemCallback<MainEntity>() {
            override fun areItemsTheSame(oldItem: MainEntity, newItem: MainEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MainEntity, newItem: MainEntity): Boolean {
                return oldItem.id == newItem.id
            }

        }
    ) {
    private var dataBoundClickListener: DataBoundClickListener<MainEntity>? = null


    fun setRecyclerViewClickListener(aRVC: DataBoundClickListener<MainEntity> ) {
        this.dataBoundClickListener = aRVC;
    }



    override fun createBinding(parent: ViewGroup): MainEntityItemBinding {
        val binding = DataBindingUtil.inflate<MainEntityItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.main_entity_item,
            parent,
            false,
            dataBindingComponent
        )

        binding.root.setOnClickListener{ view ->
            binding.mainEntity?.let { item ->
                dataBoundClickListener?.onClick(view, item)
            }
        }
        return binding
    }

    override fun bind(binding: MainEntityItemBinding, item: MainEntity) {
        binding.mainEntity = item
    }

}

