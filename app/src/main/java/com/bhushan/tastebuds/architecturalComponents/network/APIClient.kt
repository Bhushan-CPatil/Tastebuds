package com.bhushan.tastebuds.architecturalComponents.network

import com.bhushan.tastebuds.architecturalComponents.model.recipeList.MealsList
import retrofit2.Response
import retrofit2.http.GET

interface APIClient {
    @GET("randomselection.php")
    suspend fun getRecipeList(): Response<MealsList>
}