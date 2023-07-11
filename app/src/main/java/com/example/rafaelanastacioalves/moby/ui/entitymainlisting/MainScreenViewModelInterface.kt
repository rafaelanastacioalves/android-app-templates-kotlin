package com.example.rafaelanastacioalves.moby.ui.entitymainlisting

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.flow.StateFlow

interface MainScreenViewModelInterface {
    fun loadDataIfNecessary()

    val mainScreeViewState: StateFlow<MainScreeViewState>

}