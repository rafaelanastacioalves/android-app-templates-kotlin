package com.example.rafaelanastacioalves.moby.domain.interactors


import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.repository.AppRepository
import kotlinx.coroutines.flow.FlowCollector

class EntityDetailsInteractor :
        Interactor<Resource<EntityDetails>?, EntityDetailsInteractor.RequestValues>() {
    val appRepository: AppRepository

    init {
        appRepository = AppRepository
    }

    override suspend fun run(
        requestValue: RequestValues?,
        flowCollector: FlowCollector<Resource<EntityDetails>?>,
    ) {
        var result = requestValue?.requestId?.let { appRepository.entityDetails(it) }
        flowCollector.emit(
            result
        )
    }


    class RequestValues(val requestId: String) : Interactor.RequestValues



}
