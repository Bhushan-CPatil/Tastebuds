package com.bhushan.tastebuds.architecturalComponents.repository

import com.bhushan.tastebuds.architecturalComponents.network.APIClient
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: APIClient
)  {
    suspend fun getMealList() = apiHelper.getRecipeList()
}