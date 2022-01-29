package com.task.ui.component.recipes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.task.data.DataRepository
import com.task.data.Resource
import com.task.data.dto.recipes.Recipes
import com.task.data.dto.recipes.RecipesItem
import com.task.data.error.NETWORK_ERROR
import com.util.InstantExecutorExtension
import com.util.MainCoroutineRule
import com.util.TestModelsGenerator
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.internal.configuration.injection.MockInjection

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class RecipesListViewModelTest {
    private lateinit var recipesListViewModel: RecipesListViewModel

    @MockK
    private lateinit var dataRepository: DataRepository

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        recipesListViewModel = RecipesListViewModel(dataRepository)
    }

    @Test
    fun `retrieve recipes when recipe list is not empty`() {
        val recipesList = arrayListOf(RecipesItem())
        val recipes = Recipes(recipesList)
        coEvery { dataRepository.requestRecipes() } returns flow {
            emit(Resource.Success(recipes))
        }
        recipesListViewModel.getRecipes()
        recipesListViewModel.recipesLiveData.observeForever { }

        val isEmptyList = recipesListViewModel.recipesLiveData
            .value?.data?.recipesList.isNullOrEmpty()

        assertEquals(recipes, recipesListViewModel.recipesLiveData.value?.data)
        assertEquals(false, isEmptyList)
    }

    @Test
    fun `retrieve recipes when recipe list is empty`() {
        val recipes = Recipes(arrayListOf())
        coEvery { dataRepository.requestRecipes() } returns flow {
            emit(Resource.Success(recipes))
        }
        recipesListViewModel.getRecipes()
        recipesListViewModel.recipesLiveData.observeForever { }

        val isEmptyList = recipesListViewModel.recipesLiveData
            .value?.data?.recipesList.isNullOrEmpty()

        assertEquals(recipes, recipesListViewModel.recipesLiveData.value?.data)
        assertEquals(true, isEmptyList)
    }

    @Test
    fun `retrieve recipes when network request throws error`() {
        val error: Resource<Recipes> = Resource.DataError(NETWORK_ERROR)
        coEvery { dataRepository.requestRecipes() } returns flow {
            emit(error)
        }
        recipesListViewModel.getRecipes()
        recipesListViewModel.recipesLiveData.observeForever { }

        assertEquals(NETWORK_ERROR, recipesListViewModel.recipesLiveData.value?.errorCode)
    }

    @Test
    fun `Search result found when item is present in the recipe list`() {
        val recipesResponse = getRecipesResponse()
        val searchTitle = "recipe2"

        coEvery { dataRepository.requestRecipes() } returns flow {
            emit(Resource.Success(recipesResponse))
        }
        recipesListViewModel.getRecipes()

        recipesListViewModel.onSearchClick(searchTitle)
        recipesListViewModel.recipeSearchFound.observeForever { }

        assertEquals(recipesResponse.recipesList[1], recipesListViewModel.recipeSearchFound.value)
    }

    @Test
    fun `No search result found when item is not present in the recipe list`() {
        val recipesResponse = getRecipesResponse()
        val searchTitle = "*&*^%"

        coEvery { dataRepository.requestRecipes() } returns flow {
            emit(Resource.Success(recipesResponse))
        }
        recipesListViewModel.getRecipes()
        recipesListViewModel.onSearchClick(searchTitle)
        recipesListViewModel.noSearchFound.observeForever { }

        assertEquals(Unit, recipesListViewModel.noSearchFound.value)
    }

    private fun getRecipesResponse(): Recipes =
        Recipes(
            arrayListOf(
                RecipesItem(name = "recipe1"),
                RecipesItem(name = "recipe2"),
                RecipesItem(name = "recipe3"),
                RecipesItem(name = "recipe4")
            )
        )
}
