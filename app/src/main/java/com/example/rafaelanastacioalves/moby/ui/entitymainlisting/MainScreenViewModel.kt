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


/**
 * Learned from the following links:
 * [Jetpack Compose State codelab](https://developer.android.com/codelabs/jetpack-compose-state?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fjetpack-compose-for-android-developers-1%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fjetpack-compose-state#10)
 * and
 * [Flow Codelab](https://developer.android.com/codelabs/advanced-kotlin-coroutines#10)
 */

class MainScreenViewModel : ViewModel() {

    private val mainEntityListInteractor: MainEntityListInteractor = MainEntityListInteractor()

    val mainEntityListLiveData : LiveData<ViewState> = loadDataIfNecessary().map {
        ViewState(status = it.status, data = it.data, message = it.message)
    }

    private val _entityList = loadMockData().toMutableStateList()
    val entityList : List<MainEntity>
        get() = _entityList

    fun loadMockData() = listOf<MainEntity>(MainEntity("1",title = "title1", price = "10", "1", "https://thoughtcard.com/wp-content/uploads/2016/03/Trip-vs-Vacation-1030x689.jpg"),
        MainEntity("2",title = "title2", price = "10", "2", "https://thoughtcard.com/wp-content/uploads/2016/03/Trip-vs-Vacation-1030x689.jpg"),
        MainEntity("3",title = "title3", price = "10", "3", "https://thoughtcard.com/wp-content/uploads/2016/03/Trip-vs-Vacation-1030x689.jpg")
    )

    fun loadDataIfNecessary() : LiveData<Resource<List<MainEntity>>>{
            return mainEntityListInteractor.execute(viewModelScope,null).asLiveData()
    }



     class ViewState(
        status: Status = Status.LOADING,
        data: List<MainEntity>? = null,
        message: String? = null) : Resource<List<MainEntity>>(status, data, message) {

            val stateList = this.data.orEmpty().toMutableStateList()
    }

}
