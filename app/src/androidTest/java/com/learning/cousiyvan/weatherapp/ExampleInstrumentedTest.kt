package com.learning.cousiyvan.weatherapp

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.learning.cousiyvan.weatherapp.ui.activities.MainActivity
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Matcher

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.Description

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.learning.cousiyvan.weatherapp", appContext.packageName)
    }

    @get: Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    // @Test
    fun itemClickNAvigatesToDetail() {
        onView(withId(R.id.forecastList)).perform(RecyclerViewActions
            .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.weatherDescription))
            .check(matches(isAssignableFrom(TextView::class.java)))
    }

    // @Test
    fun modifyZipCode_changesToolbarTitle() {
        openActionBarOverflowOrOptionsMenu(activityRule.activity)
        onView(withText(R.string.settings)).perform(click())
        onView(withId(R.id.cityCode)).perform(replaceText("28830"))
        pressBack()
        onView(isAssignableFrom(Toolbar::class.java))
            .check(matches(withToolbarTitle(`is`("San Fernando de Henares (ES)"))))
    }

    private fun withToolbarTitle(textMatcher: Matcher<CharSequence>): Matcher<Any> =
        object : BoundedMatcher<Any, Toolbar>(Toolbar::class.java) {

            override fun matchesSafely(toolbar: Toolbar): Boolean =
                textMatcher.matches(toolbar.title)

            override fun describeTo(description: org.hamcrest.Description) {
                description.appendText("with toolbar title: ")
                textMatcher.describeTo(description)
            }
        }
}
