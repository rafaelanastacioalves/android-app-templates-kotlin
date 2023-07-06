package com.example.rafaelanastacioalves.moby.ui.entitymainlisting

import androidx.compose.runtime.MutableState

interface MainScreenViewModelInterface {
    fun loadDataIfNecessary()

    val mainScreeViewState: MutableState<MainScreeViewState>

}