package com.example.rafaelanastacioalves.moby.domain.interactors

import com.example.rafaelanastacioalves.moby.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow


abstract class Interactor<out T, in R : Interactor.RequestValues> {

    abstract suspend fun run(requestValue: R?, flowCollector: FlowCollector<T>)

    fun execute(
            scope: CoroutineScope,
            params: R?
    ) : Flow<T> {

        return flow<T> {
            run(requestValue = params, flowCollector = this)
        }
    }

    interface RequestValues
}
