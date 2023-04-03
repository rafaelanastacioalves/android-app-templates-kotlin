package com.example.rafaelanastacioalves.moby.domain.interactors

import com.example.rafaelanastacioalves.moby.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


abstract class Interactor<out T, in R : Interactor.RequestValues> {

    abstract suspend fun run(requestValue: R?): T

     open fun execute(
            scope: CoroutineScope,
            params: R?
    ) : Flow<T> {

        return flow<T> {
            emit(run(requestValue = null))
        }
    }

    interface RequestValues
}
