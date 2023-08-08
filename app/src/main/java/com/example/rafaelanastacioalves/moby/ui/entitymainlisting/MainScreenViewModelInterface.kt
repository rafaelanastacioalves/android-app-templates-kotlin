package com.example.rafaelanastacioalves.moby.ui.entitymainlisting

import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import kotlinx.coroutines.flow.Flow

interface MainScreenViewModelInterface {
    fun loadDataIfNecessary()


    val mainEntityListFlowState : Flow<ViewState>
    class ViewState(
        status: Status = Status.LOADING,
        data: List<MainEntity>? = null,
        message: String? = null,
    ) : Resource<List<MainEntity>>(status, data, message)


}