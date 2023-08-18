package com.example.rafaelanastacioalves.moby.ui.entitymainlisting;

import androidx.lifecycle.*
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.domain.interactors.Interactor
import com.example.rafaelanastacioalves.moby.domain.interactors.MainEntityListInteractor
import com.example.rafaelanastacioalves.moby.ui.entitymainlisting.MainScreenViewModelInterface.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


/**
 * Learned from the following links:
 * [Jetpack Compose State codelab](https://developer.android.com/codelabs/jetpack-compose-state?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fjetpack-compose-for-android-developers-1%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fjetpack-compose-state#10)
 * and
 * [Flow Codelab](https://developer.android.com/codelabs/advanced-kotlin-coroutines#10)
 */


class MainScreenViewModel :
    ViewModel(), MainScreenViewModelInterface{
    val _mainEntityListFlow = MutableStateFlow(ViewState())
    private val mainEntityListInteractor: MainEntityListInteractor = MainEntityListInteractor()

    override val mainEntityListFlowState: StateFlow<ViewState> = _mainEntityListFlow.asStateFlow()

    init {
        loadDataIfNecessary()
    }
    override fun loadDataIfNecessary() {
        viewModelScope.launch {
            mainEntityListInteractor.execute(viewModelScope, null).collect {
                _mainEntityListFlow.value = ViewState(
                    status = it.status,
                    data = it.data,
                    message = it.message
                )
            }
        }

    }

}