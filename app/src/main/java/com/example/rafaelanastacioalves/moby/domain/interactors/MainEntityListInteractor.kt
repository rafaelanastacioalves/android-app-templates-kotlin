package com.example.rafaelanastacioalves.moby.domain.interactors

import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.repository.AppRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class MainEntityListInteractor(appRepository: AppRepository) :
        Interactor<Resource<List<MainEntity>>, MainEntityListInteractor.RequestValues>() {



    override suspend fun run(requestValues: RequestValues?, flowCollector: FlowCollector<Resource<List<MainEntity>>>){
        var finalList: MutableList<MainEntity> = ArrayList<MainEntity>()
        var number = 0
        while (true) {
            delay(2000)
            number++
            finalList.add(0,
                MainEntity("$number","Entity number $number", "price", "reais", "url")
            )
            flowCollector.emit(
                Resource.success(finalList)
            )

        }


    }


    class RequestValues : Interactor.RequestValues// in this case we don't need nothing for this use case
}
