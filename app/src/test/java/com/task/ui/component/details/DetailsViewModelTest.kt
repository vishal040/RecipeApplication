package com.task.ui.component.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.task.data.DataRepository
import com.task.data.Resource
import com.task.data.dto.recipes.Recipes
import com.task.data.dto.recipes.RecipesItem
import com.util.InstantExecutorExtension
import com.util.MainCoroutineRule
import com.util.TestModelsGenerator
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class DetailsViewModelTest {

    @MockK
    private lateinit var dataRepository: DataRepository

    @MockK
    private lateinit var recipesItem: RecipesItem

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
    fun `init Intent Data`() {
        val recipesItem = RecipesItem()
        detailsViewModel.recipeData.observeForever { }

        detailsViewModel.initIntentData(recipesItem)

        val recipesData = detailsViewModel.recipeData.value
        assertEquals(recipesItem, recipesData)
    }

    @Test
    fun `add selected Recipe item from favourites`() {
        val isFavourites = true
        val recipesItem = RecipesItem(id = "123")
        coEvery { dataRepository.addToFavourite(recipesItem.id) } returns flow {
            emit(Resource.Success(true))
        }
        detailsViewModel.recipePrivate.value = recipesItem
        detailsViewModel.addToFavourites()
        detailsViewModel.isFavourite.observeForever { }

        val recipesData = detailsViewModel.isFavourite.value
        assertEquals(isFavourites, recipesData?.data)
    }

    @Test
    fun `remove given Recipe item from favourites`() {
        val isFavourites = false
        val recipesItem = RecipesItem(id = "123")
        coEvery { dataRepository.removeFromFavourite(recipesItem.id) } returns flow {
            emit(Resource.Success(true))
        }
        detailsViewModel.recipePrivate.value = recipesItem
        detailsViewModel.removeFromFavourites()
        detailsViewModel.isFavourite.observeForever { }

        val recipesData = detailsViewModel.isFavourite.value
        assertEquals(isFavourites, recipesData?.data)
    }

    @Test
    fun `isFavourite when`() {
        val isFavourites = true
        val recipesItem = RecipesItem(id = "123")
        coEvery { dataRepository.isFavourite(recipesItem.id) } returns flow {
            emit(Resource.Success(true))
        }
        detailsViewModel.recipePrivate.value = recipesItem
        detailsViewModel.isFavourites()
        detailsViewModel.isFavourite.observeForever { }

        val recipesData = detailsViewModel.isFavourite.value
        assertEquals(isFavourites, recipesData?.data)
    }
}
