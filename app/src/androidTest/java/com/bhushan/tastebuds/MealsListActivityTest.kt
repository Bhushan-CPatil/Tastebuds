package com.bhushan.tastebuds

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.bhushan.tastebuds.architecturalComponents.helper.Resource
import com.bhushan.tastebuds.architecturalComponents.model.recipeFormatted.FormattedMealsList
import com.bhushan.tastebuds.ui.activities.MealsListActivity
import org.junit.Rule
import org.junit.Test

class MealsListActivityTest {
    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MealsListActivity::class.java)

    @Test
    fun testActivityIsDisplayed() {
        onView(withId(R.id.recipe_list)).check(matches(isDisplayed()))
    }

}