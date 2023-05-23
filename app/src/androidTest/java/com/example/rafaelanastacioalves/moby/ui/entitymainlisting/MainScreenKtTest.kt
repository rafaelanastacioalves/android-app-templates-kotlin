package com.example.rafaelanastacioalves.moby.ui.entitymainlisting

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainScreenKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
    }

    @Test
    fun shouldDisplayScreenProperlty() {
        composeTestRule.setContent {
            setHomeFragment(mainScreenViewModel = MainScreenViewModelMock(), onNavigate = {})
        }

        composeTestRule.onRoot().printToLog("testSemantics")
        Thread.sleep(2000L)
    }
    @After
    fun tearDown() {
    }
}