package com.example.rafaelanastacioalves.moby.binding

import android.app.Activity
import androidx.databinding.DataBindingComponent

class ActivityDataBindingComponent(activity: Activity)  : DataBindingComponent {

    private val adapter =   ActivityBindingAdapter(activity)
    override fun getActivityBindingAdapter(): ActivityBindingAdapter = adapter
}