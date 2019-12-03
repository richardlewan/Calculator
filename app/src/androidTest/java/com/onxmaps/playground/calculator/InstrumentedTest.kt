package com.onxmaps.playground.calculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
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
        onView(withId(R.id.decimalInput1))
            .perform(typeText(input1))
        onView(withId(R.id.decimalInput2))
            .perform(typeText(input2))
        onView(withId(R.id.equalsButton)).perform(click())

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
        onView(withId(R.id.decimalInput1))
            .perform(typeText(input1))
        onView(withId(R.id.decimalInput2))
            .perform(typeText(input2))
        onView(withId(R.id.equalsButton)).perform(click())

        // Then - result should also be blank
        Assert.assertEquals("", mainActivity.result)
    }
}
