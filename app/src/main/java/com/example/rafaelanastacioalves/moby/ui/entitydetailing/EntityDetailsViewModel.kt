package com.example.rafaelanastacioalves.moby.ui.entitydetailing


import androidx.lifecycle.*
import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.domain.interactors.EntityDetailsInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn


class EntityDetailsViewModel : ViewModel() {

    private val _entityId = MutableStateFlow<String?>("")
    private val entityDetailsInteractor: EntityDetailsInteractor = EntityDetailsInteractor()
    @OptIn(ExperimentalCoroutinesApi::class)
    val entityDetails: StateFlow<Resource<EntityDetails>?> = _entityId.flatMapLatest { entityId ->
        if(entityId == null){
            flowOf(Resource.loading())
        }else{
            loadData(entityId)
        }
    }.stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Resource.loading()
    )

    private fun loadData(entityId: String?): Flow<Resource<EntityDetails>?> {
        return entityDetailsInteractor.execute(
            scope = viewModelScope,
            params =  entityId?.let {id ->
                EntityDetailsInteractor.RequestValues(id)
            }
        )
    }

    fun setEntityId(entityId: String?){
        _entityId.value = entityId
    }

}




