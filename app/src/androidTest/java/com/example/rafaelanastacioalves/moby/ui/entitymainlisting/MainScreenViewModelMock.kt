package com.example.rafaelanastacioalves.moby.ui.entitymainlisting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.entities.Resource

class MainScreenViewModelMock : MainScreenViewModelInterface {
    override fun loadDataIfNecessary(): LiveData<Resource<List<MainEntity>>> {
        return liveData {
            Resource.loading<Resource<List<MainEntity>>>()
            Resource.success(loadMockData())
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