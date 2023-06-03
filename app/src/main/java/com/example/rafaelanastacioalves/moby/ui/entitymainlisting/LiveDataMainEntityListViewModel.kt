package com.example.rafaelanastacioalves.moby.ui.entitymainlisting;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.domain.interactors.MainEntityListInteractor


class LiveDataMainEntityListViewModel : ViewModel() {

    private val mainEntityListLiveData_ = MutableLiveData<Resource<List<MainEntity>>>();
    val mainEntityListLiveData : LiveData<Resource<List<MainEntity>>> = mainEntityListLiveData_
    private val mainEntityListInteractor: MainEntityListInteractor = MainEntityListInteractor()



    fun loadDataIfNecessary(){
        if (mainEntityListLiveData_.value == null){
            mainEntityListInteractor.execute(viewModelScope,null, {
                handle(it)
            })
        }
    }

    private fun handle(it: Resource<List<MainEntity>>) {
        mainEntityListLiveData_.postValue(it)
    }

}
