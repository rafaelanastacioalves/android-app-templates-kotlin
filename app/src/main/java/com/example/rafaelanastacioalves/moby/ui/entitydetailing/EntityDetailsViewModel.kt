package com.example.rafaelanastacioalves.moby.ui.entitydetailing


import androidx.lifecycle.*
import com.example.rafaelanastacioalves.moby.domain.model.EntityDetails
import com.example.rafaelanastacioalves.moby.domain.model.Resource
import com.example.rafaelanastacioalves.moby.domain.interactors.EntityDetailsInteractor
import com.example.rafaelanastacioalves.moby.domain.interactors.Interactor


class EntityDetailsViewModel(
    val entityDetailsInteractor: Interactor<Resource<EntityDetails>, EntityDetailsInteractor.RequestValues>
) : ViewModel() {

    private val _entityId = MutableLiveData<String?>()

    private val _entityDetails = MutableLiveData<Resource<EntityDetails>>()
    val entityDetails: LiveData<Resource<EntityDetails>> = _entityId.switchMap { entityId ->
        if(entityId == null){
            MutableLiveData(Resource.loading())
        }else{
            loadData(entityId)
        }
    }

    private fun loadData(entityId: String?): LiveData<Resource<EntityDetails>> {
        _entityDetails.postValue(Resource.loading())
        return entityDetailsInteractor.execute(
            scope = viewModelScope,
            params =  entityId?.let {id ->
                EntityDetailsInteractor.RequestValues(id)
            }
        ).asLiveData()
    }

    fun setEntityId(entityId: String?){
        _entityId.value = entityId
    }

}

// make a factory class for EntityDetailsViewModel
@Suppress("UNCHECKED_CAST")
class EntityDetailsViewModelFactory(val interactor: EntityDetailsInteractor) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = EntityDetailsViewModel(interactor) as T
}




