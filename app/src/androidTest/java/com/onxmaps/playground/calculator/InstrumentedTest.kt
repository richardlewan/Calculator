package com.onxmaps.playground.calculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class InstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.onxmaps.playground.calculator", appContext.packageName)
    }

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)

    @Test
    fun changeText_ValidInput() {
        // Given
        val input1 = "3.0"
        val input2 = "1.0"

        // When
        enterInputsAndCalculate(input1, input2)

        // Then
        val mainActivity = activityRule.activity as MainActivity
        Assert.assertEquals("4.0", mainActivity.result)
        Assert.assertEquals("3.0 + 1.0 = 4.0", mainActivity.historyList[0])
    }

    @Test
    fun changeText_NoInput() {
        // Given
        val mainActivity = activityRule.activity as MainActivity
        mainActivity.result = ""
        val input1 = ""
        val input2 = ""

        // When - Perform click with blank inputs
        enterInputsAndCalculate(input1, input2)

        // Then - result should also be blank
        Assert.assertEquals("", mainActivity.result)
    }

    @Test
    fun changeMenuItemTest() {
        // Given
        val input1 = "10.0"
        val input2 = "5.0"

        // When
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        onView(withText(R.string.multiply_title))
            .perform(click())
        enterInputsAndCalculate(input1, input2)

        // Then
        val mainActivity = activityRule.activity as MainActivity
        Assert.assertEquals("50.0", mainActivity.result)
        Assert.assertEquals("10.0 * 5.0 = 50.0", mainActivity.historyList[0])
    }

    private fun enterInputsAndCalculate(input1:String, input2: String) {
        onView(withId(R.id.decimalInput1))
            .perform(typeText(input1))
        onView(withId(R.id.decimalInput2))
            .perform(typeText(input2))
        onView(withId(R.id.equalsButton)).perform(click())
    }
}
