package com.example.rafaelanastacioalves.moby.ui.entitydetailing


import androidx.lifecycle.*
import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.domain.interactors.EntityDetailsInteractor


class EntityDetailsViewModel : ViewModel() {

    val _entityDetails = MutableLiveData<Resource<EntityDetails>>()
    val entityDetails: LiveData<Resource<EntityDetails>> = _entityDetails
    val entityDetailsInteractor: EntityDetailsInteractor = EntityDetailsInteractor()

    fun loadData(entityId: String?): LiveData<Resource<EntityDetails>> {
        _entityDetails.postValue(Resource.loading())
        return entityDetailsInteractor.execute(
            scope = viewModelScope,
            params =  entityId?.let {id ->
                EntityDetailsInteractor.RequestValues(id)
            }
        ).asLiveData()
    }



}

