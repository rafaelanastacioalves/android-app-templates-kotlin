package com.example.rafaelanastacioalves.moby.ui.entitydetailing


import androidx.lifecycle.*
import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.domain.interactors.EntityDetailsInteractor
import com.example.rafaelanastacioalves.moby.domain.interactors.Interactor


class EntityDetailsViewModel(
    val entityDetailsInteractor: Interactor<Resource<EntityDetails>, EntityDetailsInteractor.RequestValues>
) : ViewModel() {

    val _entityDetails = MutableLiveData<Resource<EntityDetails>>()
    val entityDetails: LiveData<Resource<EntityDetails>> = _entityDetails


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

// make a factory class for EntityDetailsViewModel
@Suppress("UNCHECKED_CAST")
class EntityDetailsViewModelFactory(val interactor: EntityDetailsInteractor) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = EntityDetailsViewModel(interactor) as T
}




