package com.task.ui.component.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.task.data.DataRepository
import com.task.data.Resource
import com.task.data.dto.recipes.RecipesItem
import com.util.InstantExecutorExtension
import com.util.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class DetailsViewModelTest {

    @MockK
    private lateinit var dataRepository: DataRepository

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var detailsViewModel: DetailsViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        detailsViewModel = DetailsViewModel(dataRepository)
    }

    @Test
    fun `initRecipeData when provided valid recipe model then returns expectedRecipeData`() {
        val expectedResult = RecipesItem()
        detailsViewModel.recipeData.observeForever { }

        detailsViewModel.initRecipeData(expectedResult)

        val recipesData = detailsViewModel.recipeData.value
        assertEquals(expectedResult, recipesData)
    }

    @Test
    fun `recipe item is added to Favourites then isFavourite returns True`() {
        val expectedResult = true
        val recipesItem = RecipesItem(id = "123")
        coEvery { dataRepository.addToFavourite(recipesItem.id) } returns flow {
            emit(Resource.Success(true))
        }
        detailsViewModel.initRecipeData(recipesItem)
        detailsViewModel.addToFavourites()
        detailsViewModel.isFavourite.observeForever { }

        val recipesData = detailsViewModel.isFavourite.value
        assertEquals(expectedResult, recipesData?.data)
    }

    @Test
    fun `recipe item is not added to Favourites then isFavourite returns False`() {
        val expectedResult = false
        val recipesItem = RecipesItem(id = "123")
        coEvery { dataRepository.isFavourite(recipesItem.id) } returns flow {
            emit(Resource.Success(false))
        }
        detailsViewModel.initRecipeData(recipesItem)
        detailsViewModel.isFavourites()
        detailsViewModel.isFavourite.observeForever { }

        val recipesData = detailsViewModel.isFavourite.value
        assertEquals(expectedResult, recipesData?.data)
    }

    @Test
    fun `recipe item is already added to Favourites then isFavourite returns True`() {
        val expectedResult = true
        val recipesItem = RecipesItem(id = "123")
        coEvery { dataRepository.isFavourite(recipesItem.id) } returns flow {
            emit(Resource.Success(true))
        }
        detailsViewModel.initRecipeData(recipesItem)
        detailsViewModel.isFavourites()
        detailsViewModel.isFavourite.observeForever { }

        val recipesData = detailsViewModel.isFavourite.value
        assertEquals(expectedResult, recipesData?.data)
    }
}
