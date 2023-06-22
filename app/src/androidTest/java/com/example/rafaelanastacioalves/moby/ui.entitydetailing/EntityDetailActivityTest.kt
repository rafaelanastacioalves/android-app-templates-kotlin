import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag


@RunWith(AndroidJUnit4::class)
class EntityDetailActivityTest {

    @Rule
    @JvmField
    val activityTestRule = createAndroidComposeRule<EntityDetailActivity>()

//    @Before
//    fun setup() {
//
//    }
    @Test
    fun ifNoArgumentPassedShouldNotShowImage() {
        activityTestRule.activityRule.scenario.onActivity {}
        activityTestRule.onNodeWithTag("DetailImage").assertDoesNotExist()

    }
}