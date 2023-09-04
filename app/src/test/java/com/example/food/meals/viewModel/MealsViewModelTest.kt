package com.example.food.meals.viewModel

import app.cash.turbine.test
import com.example.domain.entity.Meal
import com.example.domain.usecase.UseCase
import com.example.food.MainCoroutineRule
import com.example.food.ui.meals.viewModel.MealsViewModel
import com.example.food.utils.RemoteStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MealsViewModelTest {

    @Mock
    private lateinit var useCase: UseCase

    private lateinit var mealsViewModel: MealsViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        mealsViewModel = MealsViewModel(useCase)
    }

    @Test
    fun testGetMealsSuccess_categoryName_successListOfMeals() = runTest {

        val categoryName = "Beef"

        val list: List<Meal> = listOf()

        Mockito.`when`(useCase.getMeals(categoryName)).thenReturn(flowOf(list))

        val job = launch {
            mealsViewModel.meals.test {
                Assert.assertEquals(RemoteStatus.Success(list), awaitItem())
            }
        }

        job.cancel()
    }

    @Test
    fun testGetMealsFailure_categoryName_failureListOfMeals() = runTest {

        val error = "Error Message"

        val categoryName = "Beef"

        Mockito.`when`(useCase.getMeals(categoryName)).thenReturn(flow {
            throw Throwable(error)
        })

        val job = launch {
            mealsViewModel.meals.test {
                Assert.assertEquals(RemoteStatus.Failure(Throwable(error)), awaitItem())
            }
        }

        job.cancel()

    }
}
