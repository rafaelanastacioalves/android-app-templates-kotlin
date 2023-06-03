package com.example.rafaelanastacioalves.moby.binding

import android.app.Activity
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import java.net.URL
import com.example.rafaelanastacioalves.moby.R
import com.squareup.picasso.Picasso

class ActivityBindingAdapter(val activity: Activity) {

    @BindingAdapter(value = ["imageUrl"], requireAll = false)
    fun bindImage(imageView: ImageView, url: String?){
        val placeholderList: StateListDrawable = activity.getResources().getDrawable(R.drawable.ic_placeholder_map_selector) as StateListDrawable;
        Picasso.get()
            .load(url)
            .placeholder(placeholderList)
            .into(imageView);    }


}
