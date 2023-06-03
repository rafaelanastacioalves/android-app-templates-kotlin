package com.example.rafaelanastacioalves.moby.ui.entitydetailing


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.domain.interactors.EntityDetailsInteractor


internal class LiveDataEntityDetailsViewModel : ViewModel() {

    val entityDetails_ = MutableLiveData<Resource<EntityDetails>>()
    val entityDetails : LiveData<Resource<EntityDetails>> = entityDetails_
    val entityDetailsInteractor: EntityDetailsInteractor = EntityDetailsInteractor()

    fun loadData(entityId: String?) : MutableLiveData<Resource<EntityDetails>> {

        entityDetails_.postValue(Resource.loading())
        entityDetailsInteractor.execute(viewModelScope,
                entityId?.let{EntityDetailsInteractor.RequestValues(it)},{ it -> handle(it)})
        return entityDetails_
    }

    private fun handle(it: Resource<EntityDetails>?) {
        entityDetails_.postValue(it)
    }


}

