package com.bhushan.tastebuds.architecturalComponents.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bhushan.tastebuds.architecturalComponents.helper.Resource
import com.bhushan.tastebuds.architecturalComponents.model.recipeFormatted.FormattedMealsList
import com.bhushan.tastebuds.architecturalComponents.model.recipeList.MealsList
import com.bhushan.tastebuds.architecturalComponents.repository.MainRepository
import com.bhushan.tastebuds.utility.Constants.EXTENTION
import com.bhushan.tastebuds.utility.Constants.FAILED
import com.bhushan.tastebuds.utility.Constants.IMAGE_BASE_URL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    val _formattedMealList = MutableLiveData<Resource<FormattedMealsList>>()
    val formattedMealList: LiveData<Resource<FormattedMealsList>> get() = _formattedMealList

    fun MealList() = CoroutineScope(Dispatchers.IO).launch {
        _formattedMealList.postValue(Resource.loading(null))
        try {
            mainRepository.getMealList().let {
                if (it.isSuccessful) {
                    _formattedMealList.postValue(Resource.success(convertToFormattedMealsList(it.body()!!)))
                } else {
                    _formattedMealList.postValue(Resource.error(FAILED, null))
                }
            }
        } catch (e: Exception) {
            _formattedMealList.postValue(Resource.error(e.message.toString(), null))
        }
    }

    fun convertToFormattedMealsList(mealsList: MealsList): FormattedMealsList {
        return FormattedMealsList(
            meals = mealsList.meals.map { meal ->
                FormattedMealsList.Meal(
                    dateModified = meal.dateModified?:"",
                    idMeal = meal.idMeal?:"",
                    strArea = meal.strArea?:"",
                    strCategory = meal.strCategory?:"",
                    strCreativeCommonsConfirmed = meal.strCreativeCommonsConfirmed?:"",
                    strDrinkAlternate = meal.strDrinkAlternate?:"",
                    strImageSource = meal.strImageSource?:"",
                    strInstructions = meal.strInstructions?:"",
                    strMeal = meal.strMeal?:"",
                    strMealThumb = meal.strMealThumb?:"",
                    strSource = meal.strSource?:"",
                    strTags = meal.strTags?:"",
                    strYoutube = meal.strYoutube?:"",
                    ingredients = extractIngredients(meal)
                )
            }
        )
    }

    private fun extractIngredients(meal: MealsList.Meal): List<FormattedMealsList.Meal.Ingredient> {
        val ingredients = mutableListOf<FormattedMealsList.Meal.Ingredient>()
        val ingredientFields = listOf(
            meal.strIngredient1, meal.strIngredient2, meal.strIngredient3, meal.strIngredient4, meal.strIngredient5,
            meal.strIngredient6, meal.strIngredient7, meal.strIngredient8, meal.strIngredient9, meal.strIngredient10,
            meal.strIngredient11, meal.strIngredient12, meal.strIngredient13, meal.strIngredient14, meal.strIngredient15,
            meal.strIngredient16, meal.strIngredient17, meal.strIngredient18, meal.strIngredient19, meal.strIngredient20
        )
        val measureFields = listOf(
            meal.strMeasure1, meal.strMeasure2, meal.strMeasure3, meal.strMeasure4, meal.strMeasure5,
            meal.strMeasure6, meal.strMeasure7, meal.strMeasure8, meal.strMeasure9, meal.strMeasure10,
            meal.strMeasure11, meal.strMeasure12, meal.strMeasure13, meal.strMeasure14, meal.strMeasure15,
            meal.strMeasure16, meal.strMeasure17, meal.strMeasure18, meal.strMeasure19, meal.strMeasure20
        )

        for (i in ingredientFields.indices) {
            val ingredient = ingredientFields[i]?:""
            if (ingredient.isNotBlank()) {
                val measure = measureFields[i]?:""
                val image = IMAGE_BASE_URL + ingredient.toLowerCase().trim() + EXTENTION
                ingredients.add(FormattedMealsList.Meal.Ingredient(image, measure, ingredient))
            }
        }

        return ingredients
    }
}