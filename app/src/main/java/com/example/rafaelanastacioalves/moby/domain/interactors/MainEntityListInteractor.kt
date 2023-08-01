package com.example.rafaelanastacioalves.moby.domain.interactors

import android.util.Log
import com.example.rafaelanastacioalves.moby.domain.model.MainEntity
import com.example.rafaelanastacioalves.moby.domain.model.Resource
import com.example.rafaelanastacioalves.moby.repository.AppRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class MainEntityListInteractor(val appRepository: AppRepository) :
        Interactor<Resource<List<MainEntity>>, MainEntityListInteractor.RequestValues>() {

    override suspend fun run(requestValues: RequestValues?, flowCollector: FlowCollector<Resource<List<MainEntity>>>){


        flowCollector.emitAll(
            appRepository.mainEntity()
        )


    }


    class RequestValues : Interactor.RequestValues// in this case we don't need nothing for this use case
}
