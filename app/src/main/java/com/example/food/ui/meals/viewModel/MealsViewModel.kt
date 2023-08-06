package com.example.food.ui.meals.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food.data.remote.entity.Meal
import com.example.food.data.repo.Repository
import com.example.food.utils.RemoteStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class MealsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var meals = MutableStateFlow<RemoteStatus<List<Meal>>>(RemoteStatus.Loading)

    fun getMeals(category: String) {
        viewModelScope.launch {
            try {
                repository.getMeals(category).catch { it ->
                    meals.value = RemoteStatus.Failure(it)
                }.collect{ it ->
                    meals.value = RemoteStatus.Success(it)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}