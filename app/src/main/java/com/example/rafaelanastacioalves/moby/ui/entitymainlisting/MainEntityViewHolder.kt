package com.example.rafaelanastacioalves.moby.ui.entitymainlisting;

import android.content.Context
import android.graphics.drawable.StateListDrawable
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.rafaelanastacioalves.moby.R
import com.example.rafaelanastacioalves.moby.databinding.DetailEntityViewholderBinding
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.listeners.RecyclerViewClickListener
import com.squareup.picasso.Picasso

class MainEntityViewHolder(private val binding: DetailEntityViewholderBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener{

    lateinit private var aRecyclerViewListener: RecyclerViewClickListener


    constructor(binding: DetailEntityViewholderBinding , clickListener: RecyclerViewClickListener) : this(binding) {
        this.aRecyclerViewListener = clickListener
    }
    init {
        binding.detailContainer.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        aRecyclerViewListener.onClick(v, getAdapterPosition());
    }

    fun bind(aMainEntity: MainEntity, context: Context) {

        binding.entityDetailTitleTextview.setText(aMainEntity.title);
        val placeholderList: StateListDrawable= context.getResources().getDrawable(R.drawable.ic_placeholder_map_selector) as StateListDrawable;
        Picasso.get()
                .load(aMainEntity.imageUrl)
                .placeholder(placeholderList)
                .into(binding.mainEntityImageview as ImageView);


    }
}
