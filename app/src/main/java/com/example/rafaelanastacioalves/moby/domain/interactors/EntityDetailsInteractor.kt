package com.example.rafaelanastacioalves.moby.domain.interactors


import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.repository.AppRepository

class EntityDetailsInteractor :
    Interactor<Resource<EntityDetails>, EntityDetailsInteractor.RequestValues>() {
    val appRepository: AppRepository

    init {
        appRepository = AppRepository
    }

    override suspend fun run(requestValue: EntityDetailsInteractor.RequestValues?): Resource<EntityDetails> {
        return requestValue?.requestId.let { it?.let { param -> appRepository.entityDetails(param) } }
            ?: Resource(
                Resource.Status.GENERIC_ERROR,
                data = null,
                "Nenhum parametro enviado para API"
            )
    }

    class RequestValues(val requestId: String) : Interactor.RequestValues

}
