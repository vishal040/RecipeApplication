package com.task.data.remote

import com.task.data.Resource
import com.task.data.dto.recipes.Recipes

internal interface RemoteDataSource {
    suspend fun requestRecipes(): Resource<Recipes>
}
