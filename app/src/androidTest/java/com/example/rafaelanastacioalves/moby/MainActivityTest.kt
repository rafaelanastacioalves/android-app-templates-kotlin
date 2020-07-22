package com.example.rafaelanastacioalves.moby


import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.rafaelanastacioalves.moby.ui.entitymainlisting.MainActivity
import com.example.rafaelanastacioalves.moby.repository.database.AppDataBase
import com.example.rafaelanastacioalves.moby.util.RestServiceTestHelper.getStringFromFile
import com.example.rafaelanastacioalves.moby.util.ViewMatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var mainActivityActivityTestRule = ActivityTestRule(MainActivity::class.java, true, false)
    private val fileNameTripPackagesOKResponse : String = "main_entity_ok_responsee.json"
    private val fileNameTripPackagesAditionalOKResponse = "main_entity_ok_responsee_aditional.json"
    private var server: MockWebServer? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        server = MockWebServer()
        server!!.start(1234)
        InstrumentationRegistry.registerInstance(InstrumentationRegistry.getInstrumentation(), Bundle())
        server!!.url("/").toString()

        InstrumentationRegistry.getInstrumentation().targetContext.deleteDatabase(AppDataBase.databaseName)
    }

    @Test
    fun should_ShowTripPackageSuccess_WhenAPIIsSuccess() {
        server!!.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(getStringFromFile(
                        InstrumentationRegistry.getInstrumentation().context
                        , fileNameTripPackagesOKResponse)
                )
        )
        server!!.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(getStringFromFile(
                        InstrumentationRegistry.getInstrumentation().context
                        , fileNameTripPackagesAditionalOKResponse)
                )
        )
        val intent = Intent()
        mainActivityActivityTestRule.launchActivity(intent)
        onView(allOf(withId(R.id.entity_detail_title_textview), withText("Disney Premium Additional"))).check(matches(isDisplayed()))
    }

    @Test
    @Throws(IOException::class)
    fun should_show2Items_WhenAPIGives2Items() {
        server!!.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(getStringFromFile(
                        InstrumentationRegistry.getInstrumentation().context
                        , fileNameTripPackagesOKResponse)
                )
        )
        server!!.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(getStringFromFile(
                        InstrumentationRegistry.getInstrumentation().context
                        , fileNameTripPackagesAditionalOKResponse)
                )
        )
        val intent = Intent()
        mainActivityActivityTestRule.launchActivity(intent)
        val testedPosition = 0  
        onView(withId(R.id.main_entity_list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(testedPosition))
        onView(withId(R.id.main_entity_list)).check(matches(ViewMatcher.showMainItemWithTitle("Disney Premium Additional", testedPosition)))
    }


    @After
    @Throws(Exception::class)
    fun tearDown() {
        server!!.shutdown()
    }
}
