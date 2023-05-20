package com.example.rafaelanastacioalves.moby.ui.entitymainlisting

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.junit4.ComposeTestRule

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
        Thread.sleep(5000)

    }
    @After
    fun tearDown() {
    }
}