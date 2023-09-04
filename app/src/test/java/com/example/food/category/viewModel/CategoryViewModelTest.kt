package com.example.food.category.viewModel

import app.cash.turbine.test
import com.example.domain.entity.Category
import com.example.domain.usecase.UseCase
import com.example.food.MainCoroutineRule
import com.example.food.ui.category.viewModel.CategoryViewModel
import com.example.food.utils.RemoteStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CategoryViewModelTest {

    @Mock
    private lateinit var useCase: UseCase

    private lateinit var categoryViewModel: CategoryViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        categoryViewModel = CategoryViewModel(useCase)
    }

    @Test
    fun testGetCategorySuccess_nothing_successListOfCategory() = runTest {

        val list: List<Category> = listOf(
            Category("Beef", "https://www.themealdb.com/images/category/beef.png"),
            Category("Chicken", "https://www.themealdb.com/images/category/chicken.png"),
            Category("Dessert", "https://www.themealdb.com/images/category/dessert.png"),
            Category("Lamb", "https://www.themealdb.com/images/category/lamb.png"),
            Category("Miscellaneous", "https://www.themealdb.com/images/category/miscellaneous.png"),
            Category("Pasta", "https://www.themealdb.com/images/category/pasta.png"),
            Category("Pork", "https://www.themealdb.com/images/category/pork.png"),
            Category("Seafood", "https://www.themealdb.com/images/category/seafood.png"),
            Category("Side", "https://www.themealdb.com/images/category/side.png"),
            Category("Starter", "https://www.themealdb.com/images/category/starter.png"),
            Category("Vegan", "https://www.themealdb.com/images/category/vegan.png"),
            Category("Vegetarian", "https://www.themealdb.com/images/category/vegetarian.png"),
            Category("Breakfast", "https://www.themealdb.com/images/category/breakfast.png"),
            Category("Goat", "https://www.themealdb.com/images/category/goat.png")
        )

        Mockito.`when`(useCase.getCategories()).thenReturn(flowOf(list))

        val job = launch {
            categoryViewModel.categories.test {
                assertEquals(RemoteStatus.Success(list), awaitItem())
            }
        }

        job.cancel()

    }

    @Test
    fun testGetCategoryFailure_nothing_failureListOfCategory() = runTest {

        val error = "Error Message"

        Mockito.`when`(useCase.getCategories()).thenReturn(flow {
            throw Throwable(error)
        })

        val job = launch {
            categoryViewModel.categories.test {
                assertEquals(RemoteStatus.Failure(Throwable(error)), awaitItem())
            }
        }

        job.cancel()

    }

}