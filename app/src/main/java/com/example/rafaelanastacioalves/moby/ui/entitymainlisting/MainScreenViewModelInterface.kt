package com.example.rafaelanastacioalves.moby.ui.entitymainlisting

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.flow.StateFlow

/**
 * Just creating an interface so we can adapt its implementation for test purposes,
 * as explained in [Google Code Labs](https://developer.android.com/codelabs/advanced-android-kotlin-training-testing-test-doubles#5 )
 *
 */
interface MainScreenViewModelInterface {
    fun loadDataIfNecessary()

    val mainScreeViewState: StateFlow<MainScreeViewState>

}