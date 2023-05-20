package com.example.rafaelanastacioalves.moby.ui.entitymainlisting;

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.domain.interactors.MainEntityListInteractor
import kotlinx.coroutines.flow.Flow
import androidx.lifecycle.*
import com.example.rafaelanastacioalves.moby.domain.interactors.Interactor
import com.example.rafaelanastacioalves.moby.ui.entitymainlisting.MainScreenViewModelInterface.*


/**
 * Learned from the following links:
 * [Jetpack Compose State codelab](https://developer.android.com/codelabs/jetpack-compose-state?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fjetpack-compose-for-android-developers-1%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fjetpack-compose-state#10)
 * and
 * [Flow Codelab](https://developer.android.com/codelabs/advanced-kotlin-coroutines#10)
 */


class MainScreenViewModel(val mainEntityListInteractor: Interactor<Resource<List<MainEntity>>, MainEntityListInteractor.RequestValues>) :
    ViewModel(),
    MainScreenViewModelInterface {


    override fun loadDataIfNecessary(): LiveData<Resource<List<MainEntity>>> {
        return mainEntityListInteractor.execute(viewModelScope, null).asLiveData()
    }


}

@Suppress("UNCHECKED_CAST")
class MainScreenViewModelFactory(val mainEntityListInteractor: Interactor<Resource<List<MainEntity>>, MainEntityListInteractor.RequestValues>) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (MainScreenViewModel(mainEntityListInteractor) as T)

}