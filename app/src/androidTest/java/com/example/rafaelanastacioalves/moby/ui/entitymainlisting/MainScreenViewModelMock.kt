package com.example.rafaelanastacioalves.moby.ui.entitymainlisting

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.rafaelanastacioalves.moby.domain.model.MainEntity
import com.example.rafaelanastacioalves.moby.domain.model.Resource

class MainScreenViewModelMock : MainScreenViewModelInterface {

    override val mainEntityListLiveData: LiveData<MainScreenViewModelInterface.ViewState>

    init {
        mainEntityListLiveData = loadDataIfNecessary()
    }
    override fun loadDataIfNecessary(): LiveData<MainScreenViewModelInterface.ViewState> {
        return liveData {
            emit(Resource.loading())
            emit(Resource.success(loadMockData()))
        }.map {
            MainScreenViewModelInterface.ViewState(
                status = it.status,
                data = it.data,
                message = it.message
            )
        }
    }



    fun loadMockData() = listOf<MainEntity>(
        MainEntity(
            "1",
            title = "title1",
            price = "10",
            "1",
            "https://thoughtcard.com/wp-content/uploads/2016/03/Trip-vs-Vacation-1030x689.jpg"
        ),
        MainEntity(
            "2",
            title = "title2",
            price = "10",
            "2",
            "https://thoughtcard.com/wp-content/uploads/2016/03/Trip-vs-Vacation-1030x689.jpg"
        ),
        MainEntity(
            "3",
            title = "title3",
            price = "10",
            "3",
            "https://thoughtcard.com/wp-content/uploads/2016/03/Trip-vs-Vacation-1030x689.jpg"
        )
    )
}