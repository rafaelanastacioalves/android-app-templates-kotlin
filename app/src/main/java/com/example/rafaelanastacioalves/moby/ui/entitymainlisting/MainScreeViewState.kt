package com.example.rafaelanastacioalves.moby.ui.entitymainlisting

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.entities.Resource

/**
 * Simple ViewState Class for Compose library usage. If one "status" or "datalist" changes, a recomposition should occurk
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