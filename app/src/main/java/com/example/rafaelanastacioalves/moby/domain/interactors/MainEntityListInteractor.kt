package com.example.rafaelanastacioalves.moby.domain.interactors

import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.repository.AppRepository
import kotlinx.coroutines.flow.*

class MainEntityListInteractor :
        Interactor<Resource<List<MainEntity>>, MainEntityListInteractor.RequestValues>() {

    val appRepository: AppRepository


    init {
        appRepository = AppRepository
    }

    override suspend fun run(requestValues: RequestValues?): Resource<List<MainEntity>> {
        var finalList: List<MainEntity> = ArrayList<MainEntity>()

            // in this examaple we could call sequentially or wait for one result so we get some data to make another call, just saying...
            val deferredOne = appRepository.mainEntity()
            val deferredTwo =  appRepository.mainEntityAdditional()

            val resultOne: List<MainEntity> = deferredOne.data.orEmpty()
            val resultTwo: List<MainEntity> = deferredTwo.data.orEmpty()

            return Resource.success(resultOne + resultTwo)


    }


    class RequestValues : Interactor.RequestValues// in this case we don't need nothing for this use case
}
