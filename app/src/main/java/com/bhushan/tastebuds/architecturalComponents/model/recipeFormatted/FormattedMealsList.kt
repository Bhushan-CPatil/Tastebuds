package com.bhushan.tastebuds.architecturalComponents.model.recipeFormatted

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FormattedMealsList(
    val meals: List<Meal>
) : Parcelable {
    @Parcelize
    data class Meal(
        val dateModified: String,
        val idMeal: String,
        val ingredients: List<Ingredient>,
        val strArea: String,
        val strCategory: String,
        val strCreativeCommonsConfirmed: String,
        val strDrinkAlternate: String,
        val strImageSource: String,
        val strInstructions: String,
        val strMeal: String,
        val strMealThumb: String,
        val strSource: String,
        val strTags: String,
        val strYoutube: String
    ) : Parcelable {
        @Parcelize
        data class Ingredient(
            val image: String,
            val measure: String,
            val strIngredient: String
        ) : Parcelable
    }
}