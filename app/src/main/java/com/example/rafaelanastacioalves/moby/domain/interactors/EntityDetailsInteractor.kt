package com.example.rafaelanastacioalves.moby.domain.interactors


import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.repository.AppRepository
import kotlinx.coroutines.flow.FlowCollector

class EntityDetailsInteractor :
    Interactor<Resource<EntityDetails>, EntityDetailsInteractor.RequestValues>() {
    val appRepository: AppRepository

    init {
        appRepository = AppRepository
    }


    class RequestValues(val requestId: String) : Interactor.RequestValues

    override suspend fun run(
        requestValue: RequestValues?,
        flowCollector: FlowCollector<Resource<EntityDetails>>,
    ){
        flowCollector.emit(requestValue?.requestId.let { it?.let { param -> appRepository.entityDetails(param) } }
            ?: Resource(
                Resource.Status.GENERIC_ERROR,
                data = null,
                "Nenhum parametro enviado para API"
            ))
    }

}
