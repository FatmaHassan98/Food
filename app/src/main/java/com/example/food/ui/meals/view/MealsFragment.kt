package com.example.food.ui.meals.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food.databinding.FragmentMealsBinding
import com.example.food.ui.meals.viewModel.MealsViewModel
import com.example.food.utils.RemoteStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MealsFragment : Fragment() {

    private lateinit var binding: FragmentMealsBinding
    private val mealsViewModel by viewModels<MealsViewModel>()
    private lateinit var mealAdapter : MealAdapter
    private val args: MealsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMealsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            mealsViewModel.getMeals(args.category)
        }
        lifecycleScope.launch {
            mealsViewModel.meals.collectLatest {
                when (it) {
                    is RemoteStatus.Loading -> {
                        binding.MealsRecycler.visibility = View.GONE
                        binding.progressMeals.visibility = View.VISIBLE
                    }

                    is RemoteStatus.Success -> {
                        binding.MealsRecycler.visibility = View.VISIBLE
                        binding.progressMeals.visibility = View.GONE
                        mealAdapter = MealAdapter()

                        binding.MealsRecycler.apply {
                            adapter = mealAdapter
                            mealAdapter.submitList(it.data)
                            layoutManager = LinearLayoutManager(requireContext()).apply {
                                orientation = RecyclerView.VERTICAL
                            }
                        }

                    }
                    else ->{

                    }
                }
            }
        }
    }

}