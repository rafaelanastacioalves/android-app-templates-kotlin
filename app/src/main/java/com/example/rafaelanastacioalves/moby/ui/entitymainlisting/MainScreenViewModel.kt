package com.example.rafaelanastacioalves.moby.ui.entitymainlisting;

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.domain.interactors.MainEntityListInteractor


class MainScreenViewModel : ViewModel() {


    private val _mainEntityListLiveData = MutableLiveData<Resource<List<MainEntity>>>();
    private val mainEntityListInteractor: MainEntityListInteractor = MainEntityListInteractor()

    private val _entityList = loadMockData().toMutableStateList()
    val entityList : List<MainEntity>
        get() = _entityList

    fun loadMockData() = listOf<MainEntity>(MainEntity("1",title = "title1", price = "10", "1", "https://thoughtcard.com/wp-content/uploads/2016/03/Trip-vs-Vacation-1030x689.jpg"),
        MainEntity("2",title = "title2", price = "10", "2", "https://thoughtcard.com/wp-content/uploads/2016/03/Trip-vs-Vacation-1030x689.jpg"),
        MainEntity("3",title = "title3", price = "10", "3", "https://thoughtcard.com/wp-content/uploads/2016/03/Trip-vs-Vacation-1030x689.jpg")
    )

    fun loadDataIfNecessary(){
        if (_mainEntityListLiveData.value == null){
            mainEntityListInteractor.execute(viewModelScope,null, {
                handle(it)
            })
        }
    }

    private fun handle(listResource: Resource<List<MainEntity>>) {
       if (listResource.data!=null) {
            _entityList.clear()
            _entityList.addAll(listResource.data)
       }
    }

}
