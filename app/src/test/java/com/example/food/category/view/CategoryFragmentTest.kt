package com.example.food.category.view

import com.example.food.databinding.FragmentCategoryBinding
import com.example.food.ui.category.view.CategoryFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CategoryFragmentTest {

    private lateinit var categoryFragment: CategoryFragment

    private lateinit var binding : FragmentCategoryBinding

    @Before
    fun setUp(){
        categoryFragment = CategoryFragment()
    }

    @Test
    fun testFragmentNotNull(){

        val recyclerView = binding.categoryRecycler

        recyclerView.findViewHolderForAdapterPosition(0)?.itemView?.performClick()

    }
}