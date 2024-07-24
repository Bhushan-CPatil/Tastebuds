package com.bhushan.tastebuds

import android.content.Intent
import android.net.Uri
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bhushan.tastebuds.architecturalComponents.model.recipeFormatted.FormattedMealsList
import com.bhushan.tastebuds.ui.activities.MealDetailsActivity
import junit.framework.TestCase.assertTrue
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MealDetailsActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MealDetailsActivity::class.java)

    @Test
    fun testActivityLaunch() {
        onView(withId(R.id.title)).check(matches(isDisplayed()))
        onView(withId(R.id.description)).check(matches(isDisplayed()))
        onView(withId(R.id.time)).check(matches(isDisplayed()))
        onView(withId(R.id.itemcount)).check(matches(isDisplayed()))
        onView(withId(R.id.openlink)).check(matches(isDisplayed()))
        onView(withId(R.id.thumbnail_image)).check(matches(isDisplayed()))
        onView(withId(R.id.close)).check(matches(isDisplayed()))
    }

    @Test
    fun testMealDataDisplayed() {
        val meal = FormattedMealsList.Meal(
            dateModified = "2024-01-01",
            idMeal = "1",
            strArea = "American",
            strCategory = "Dessert",
            strCreativeCommonsConfirmed = "Yes",
            strDrinkAlternate = "",
            strImageSource = "",
            strInstructions = "Mix well and bake.",
            strMeal = "Chocolate Cake",
            strMealThumb = "https://www.themealdb.com/images/media/meals/45xxr21593348847.jpg",
            strSource = "Food Network",
            strTags = "Dessert",
            strYoutube = "https://www.youtube.com/watch?v=lyqDmUroZrI",
            ingredients = listOf(
                FormattedMealsList.Meal.Ingredient(
                    image = "https://www.themealdb.com/images/ingredients/flour.png",
                    measure = "2 cups",
                    strIngredient = "Flour"
                ),
                FormattedMealsList.Meal.Ingredient(
                    image = "https://www.themealdb.com/images/ingredients/sugar.png",
                    measure = "1 cup",
                    strIngredient = "Sugar"
                )
            )
        )

        val intent = Intent(ApplicationProvider.getApplicationContext(), MealDetailsActivity::class.java).apply {
            putExtra("mealItem", meal)
        }
        val activityScenario = ActivityScenario.launch<MealDetailsActivity>(intent)

        onView(withId(R.id.title)).check(matches(withText("Chocolate Cake")))
        onView(withId(R.id.description)).check(matches(withText("Mix well and bake.")))
        onView(withId(R.id.itemcount)).check(matches(withText("2 Items")))
    }

    @Test
    fun testCloseButton() {
        val meal = FormattedMealsList.Meal(
            dateModified = "2024-01-01",
            idMeal = "1",
            strArea = "American",
            strCategory = "Dessert",
            strCreativeCommonsConfirmed = "Yes",
            strDrinkAlternate = "",
            strImageSource = "",
            strInstructions = "Mix well and bake.",
            strMeal = "Chocolate Cake",
            strMealThumb = "https://www.themealdb.com/images/media/meals/45xxr21593348847.jpg",
            strSource = "Food Network",
            strTags = "Dessert",
            strYoutube = "https://www.youtube.com/watch?v=lyqDmUroZrI",
            ingredients = listOf(
                FormattedMealsList.Meal.Ingredient(
                    image = "https://www.themealdb.com/images/ingredients/flour.png",
                    measure = "2 cups",
                    strIngredient = "Flour"
                ),
                FormattedMealsList.Meal.Ingredient(
                    image = "https://www.themealdb.com/images/ingredients/sugar.png",
                    measure = "1 cup",
                    strIngredient = "Sugar"
                )
            )
        )

        val intent = Intent(ApplicationProvider.getApplicationContext(), MealDetailsActivity::class.java).apply {
            putExtra("mealItem", meal)
        }

        val activityScenario = ActivityScenario.launch<MealDetailsActivity>(intent)

        onView(withId(R.id.close)).perform(click())
        activityScenario.onActivity { activity ->
            assertTrue(activity.isFinishing)
        }
    }
    /*
    @Test
    fun testOpenLink() {
        Intents.init()

        try {
            val meal = FormattedMealsList.Meal(
                dateModified = "2024-01-01",
                idMeal = "1",
                strArea = "American",
                strCategory = "Dessert",
                strCreativeCommonsConfirmed = "Yes",
                strDrinkAlternate = "",
                strImageSource = "",
                strInstructions = "Mix well and bake.",
                strMeal = "Chocolate Cake",
                strMealThumb = "https://www.themealdb.com/images/media/meals/45xxr21593348847.jpg",
                strSource = "Food Network",
                strTags = "Dessert",
                strYoutube = "https:\\/\\/www.youtube.com\\/watch?v=RnsWwHcpKiY",
                ingredients = listOf(
                    FormattedMealsList.Meal.Ingredient(
                        image = "https://www.themealdb.com/images/ingredients/flour.png",
                        measure = "2 cups",
                        strIngredient = "Flour"
                    ),
                    FormattedMealsList.Meal.Ingredient(
                        image = "https://www.themealdb.com/images/ingredients/sugar.png",
                        measure = "1 cup",
                        strIngredient = "Sugar"
                    )
                )
            )

            val intent = Intent(ApplicationProvider.getApplicationContext(), MealDetailsActivity::class.java).apply {
                putExtra("mealItem", meal)
            }
            val activityScenario = ActivityScenario.launch<MealDetailsActivity>(intent)

            onView(withId(R.id.openlink)).perform(click())

            intended(allOf(
                hasAction(Intent.ACTION_VIEW),
                hasData(Uri.parse("https://www.youtube.com/watch?v=lyqDmUroZrI"))
            ))
        } finally {
            Intents.release()
        }
    }*/
}