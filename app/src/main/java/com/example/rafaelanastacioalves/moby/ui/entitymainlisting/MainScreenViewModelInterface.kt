package com.example.rafaelanastacioalves.moby.ui.entitymainlisting

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.entities.Resource

interface MainScreenViewModelInterface {
    fun loadDataIfNecessary(): LiveData<ViewState>


    val mainEntityListLiveData : LiveData<ViewState>
    class ViewState(
        status: Status = Status.LOADING,
        data: List<MainEntity>? = null,
        message: String? = null,
    ) : Resource<List<MainEntity>>(status, data, message) {

        val stateList = data.orEmpty().toMutableStateList()
    }

}