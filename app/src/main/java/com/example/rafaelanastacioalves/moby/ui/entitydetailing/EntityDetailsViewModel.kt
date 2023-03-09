package com.example.rafaelanastacioalves.moby.ui.entitydetailing


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.domain.interactors.EntityDetailsInteractor


 class EntityDetailsViewModel : ViewModel() {

    val entityDetails = MutableLiveData<Resource<EntityDetails>>()

    val entityDetailsInteractor: EntityDetailsInteractor = EntityDetailsInteractor()

    fun loadData(entityId: String?) : MutableLiveData<Resource<EntityDetails>> {

        entityDetails.postValue(Resource.loading())
        entityDetailsInteractor.execute(viewModelScope,
                entityId?.let{EntityDetailsInteractor.RequestValues(it)},{ it -> handle(it)})
        return entityDetails
    }

    private fun handle(it: Resource<EntityDetails>?) {
        entityDetails.postValue(it)
    }


}

