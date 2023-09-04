package com.example.food.ui.category.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.food.databinding.FragmentCategoryBinding
import com.example.food.ui.category.viewModel.CategoryViewModel
import com.example.food.utils.RemoteStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
class CategoryFragment : Fragment(), OnItemCategoryClicked {

    private lateinit var binding: FragmentCategoryBinding
    private val categoryViewModel by viewModels<CategoryViewModel>()
    private lateinit var categoryAdapter: CategoryAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            categoryViewModel.categories.collectLatest {
                when (it) {
                    is RemoteStatus.Loading -> {
                        binding.categoryRecycler.visibility = View.GONE
                        binding.progressCategory.visibility = View.VISIBLE
                    }

                    is RemoteStatus.Success -> {
                        binding.categoryRecycler.visibility = View.VISIBLE
                        binding.progressCategory.visibility = View.GONE
                        categoryAdapter = CategoryAdapter(this@CategoryFragment)

                        binding.categoryRecycler.apply {
                            adapter = categoryAdapter
                            categoryAdapter.submitList(it.data)
                            layoutManager = GridLayoutManager(requireContext(), 2)
                        }

                    }

                    else -> {

                    }
                }
            }
        }
    }

    override fun categoryClicked(category: String) {
        val action = CategoryFragmentDirections.actionCategoryFragmentToMealsFragment(category)
        binding.root.findNavController().navigate(action)
    }

}