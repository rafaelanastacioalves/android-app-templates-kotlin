package com.example.rafaelanastacioalves.moby.domain.interactors

import android.util.Log
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext

class MainEntityListInteractor :
    Interactor<Resource<List<MainEntity>>, MainEntityListInteractor.RequestValues>() {

    val appRepository: AppRepository


    init {
        appRepository = AppRepository
    }

    /**
     * This method has an issue with emitting items inside withContext that need to be worded out.
     * The error is  FATAL EXCEPTION: main
    Process: com.example.rafaelanastacioalves.moby.debug, PID: 10848
    java.lang.IllegalStateException: Flow invariant is violated:
    Flow was collected in [StandaloneCoroutine{Active}@94afcb6, Dispatchers.Main.immediate],
    but emission happened in [DispatchedCoroutine{Active}@8b13db7, Dispatchers.Main].
    Please refer to 'flow' documentation or use 'flowOn' instead
    at kotlinx.coroutines.flow.internal.SafeCollector_commonKt.checkContext(SafeCollector.common.kt:85)
    at kotlinx.coroutines.flow.internal.SafeCollector.checkContext(SafeCollector.kt:106)
    at kotlinx.coroutines.flow.internal.SafeCollector.emit(SafeCollector.kt:83)
    at kotlinx.coroutines.flow.internal.SafeCollector.emit(SafeCollector.kt:66)
    at com.example.rafaelanastacioalves.moby.domain.interactors.MainEntityListInteractor$run$2$1.invokeSuspend(MainEntityListInteractor.kt:57)
     *
     */
    override suspend fun run(
        requestValue: RequestValues?,
        flowCollector: FlowCollector<Resource<List<MainEntity>>>,
    ) {


        withContext(Dispatchers.IO) {

            // in this examaple we could call sequentially or wait for one result so we get some data to make another call, just saying...
//                Log.d(
//                    this@MainEntityListInteractor.javaClass.simpleName,
//                    "Fetching from repository..."
//                )
                val deferredOne = async { appRepository.mainEntity() }
                val deferredTwo = async { appRepository.mainEntityAdditional() }

                val resultOne: Resource<List<MainEntity>> = deferredOne.await()
                val resultTwo: Resource<List<MainEntity>> = deferredTwo.await()
//                Log.d(
//                    this@MainEntityListInteractor.javaClass.simpleName,
//                    "Fetch from repository ok. Going into datavalidation and emitting)"
//                )
                if (resultOne.status == Resource.Status.SUCCESS && resultTwo.status == Resource.Status.SUCCESS
                    && resultOne.data != null && resultTwo.data != null
                ) {
                    val finalList = resultOne.data + resultTwo.data
                    val result = Resource.success(finalList)
                    Log.d(
                        this@MainEntityListInteractor.javaClass.simpleName,
                        "Final Result SUCCESS:\n resultOne error: ${resultOne.status} \n resultTwo error: ${resultTwo.status})"
                    )

                    withContext(Dispatchers.Main){
                        flowCollector.emit(
                            result
                        )
                    }

                    return@withContext
                }
                Log.e(
                    this@MainEntityListInteractor.javaClass.simpleName,
                    "Something went wrong:\n resultOne error: ${resultOne.message} \n resultTwo error: ${resultTwo.message})"
                )
                withContext(Dispatchers.Main){
                    flowCollector.emit(
                        Resource.error(
                            Resource.Status.GENERIC_ERROR,
                            null,
                            "Something went wrong:\n resultOne error: ${resultOne.message} \n resultTwo error: ${resultTwo.message})"
                        )
                    )
                }
        }


    }


    class RequestValues :
        Interactor.RequestValues// in this case we don't need nothing for this use case


}
