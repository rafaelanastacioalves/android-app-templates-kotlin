package com.example.rafaelanastacioalves.moby.ui.entitymainlisting

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.domain.interactors.Interactor
import com.example.rafaelanastacioalves.moby.domain.interactors.MainEntityListInteractor
import kotlinx.coroutines.flow.FlowCollector
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainScreenViewModelTest {
    //add InstantExecutorRule
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    lateinit var mainScreenViewModel: MainScreenViewModel

    @Before
    fun setUp() {
        mainScreenViewModel = MainScreenViewModel(FakeMainEntityListInteractor())
    }

    private val observer: Observer<Resource<List<MainEntity>>>
        get() {
            val observer = Observer<Resource<List<MainEntity>>> {}
            return observer
        }

    @Test
    fun When_LoadingData_Should_ReturnLiveData() {

        val viewModel = mainScreenViewModel

        //just keep live data observed
        //ensure the method to fetch data is called
        viewModel.mainEntityListLiveData.observeForever(observer)

        // get the value
        val value = viewModel.mainEntityListLiveData.value

        //assert the value
        assertNotNull(value)
        assert(value?.data?.get(0)?.id == "1")
    }

    @After
    fun tearDown() {
        mainScreenViewModel.mainEntityListLiveData.removeObserver(observer)
    }

}





class FakeMainEntityListInteractor : Interactor<Resource<List<MainEntity>>, MainEntityListInteractor.RequestValues>() {

    // returns a flow collector that emits a Resource.success with a list of MainEntity
    override suspend fun run(
        requestValue: MainEntityListInteractor.RequestValues?,
        flowCollector: FlowCollector<Resource<List<MainEntity>>>,
    ) {
        flowCollector.emit(
            Resource.success(
                listOf(
                    MainEntity("1",title = "title1", price = "10", "1", "https://thoughtcard.com/wp-content/uploads/2016/03/Trip-vs-Vacation-1030x689.jpg")
                )
            )
        )
    }
}