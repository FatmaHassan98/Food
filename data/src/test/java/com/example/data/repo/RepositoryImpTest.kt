package com.example.data.repo

import com.example.data.remote.AppServices
import com.example.domain.entity.Category
import com.example.domain.entity.CategoryResponse
import com.example.domain.entity.Meal
import com.example.domain.entity.MealResponse
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepositoryImpTest {

    @Mock
    private lateinit var appServices: AppServices

    private lateinit var repositoryImp: RepositoryImp

    @Before
    fun setUp(){
        repositoryImp = RepositoryImp(appServices)
    }

    @After
    fun tearDown(){

    }

    @Test
    fun tesGetCategoriesEmpty_nothing_emptyListOfCategories() = runTest{

        val list : List<Category> = listOf()

        Mockito.`when`(appServices.getAllCategory()).thenReturn(CategoryResponse(list))

        val result = repositoryImp.getCategories()

        assert(result.categories == list)
    }

    @Test
    fun tesGetCategories_nothing_ListOfCategories() = runTest{

        val list : List<Category> = listOf(
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

        Mockito.`when`(appServices.getAllCategory()).thenReturn(CategoryResponse(list))

        val result = repositoryImp.getCategories()

        assert(result.categories.size == 14)
    }

    @Test
    fun testGetMealsEmpty_categoryName_emptyListOfMeals() = runTest {

        val list : List<Meal> = listOf()

        val categoryNameForTest = "Goat"

        Mockito.`when`(appServices.getMealsByCategories(categoryNameForTest)).thenReturn(MealResponse(list))

        val result = repositoryImp.getMeals(categoryNameForTest)

        Mockito.verify(appServices).getMealsByCategories(categoryNameForTest)

        assert(result.meals == list)

    }

    @Test
    fun testGetMeals_categoryName_ListOfMeals() = runTest {

        val list : List<Meal> = listOf(
            Meal("Mbuzi Choma (Roasted Goat)",  "https://www.themealdb.com/images/media/meals/cuio7s1555492979.jpg")
        )

        val categoryNameForTest = "Goat"

        Mockito.`when`(appServices.getMealsByCategories(categoryNameForTest)).thenReturn(MealResponse(list))

        val result = repositoryImp.getMeals(categoryNameForTest)

        Mockito.verify(appServices).getMealsByCategories(categoryNameForTest)

        assert(result.meals.size == 1)

    }
}