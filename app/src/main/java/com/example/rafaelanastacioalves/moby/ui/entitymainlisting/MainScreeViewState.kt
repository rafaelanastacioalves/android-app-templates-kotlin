package com.example.rafaelanastacioalves.moby.ui.entitymainlisting

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.rafaelanastacioalves.moby.domain.model.MainEntity
import com.example.rafaelanastacioalves.moby.domain.model.Resource

/**
 * Simple ViewState Class for Compose library usage. If one "status" or "datalist" changes, a recomposition should occur.
 * Based on the a complex [ViewState class](https://github.com/android/compose-samples/blob/9ceb220fcd9cce6aadb28cbe160441be2c2793b3/Crane/app/src/main/java/androidx/compose/samples/crane/calendar/model/CalendarUiState.kt)
 */
data class MainScreeViewState(
    var status: MutableState<Any> = mutableStateOf(Resource.Status.LOADING),
    private val dataList: SnapshotStateList<MainEntity> = mutableStateListOf<MainEntity>(),
    val message: String? = null,
) {

    fun setStateList(list: List<MainEntity>) {
        dataList.apply {
            clear()
            addAll(list)
            status.value = Resource.Status.SUCCESS
        }

    }

    val stateList = dataList
}