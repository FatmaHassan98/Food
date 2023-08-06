package com.example.food.ui.meals.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Meal
import com.example.domain.usecase.UseCase
import com.example.food.utils.RemoteStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(private val useCase: UseCase) : ViewModel() {

    private val _meals = MutableStateFlow<RemoteStatus<List<Meal>>>(RemoteStatus.Loading)
    val meals : StateFlow<RemoteStatus<List<Meal>>> = _meals

    fun getMeals(category: String) {
        viewModelScope.launch {
            try {
                useCase.getMeals(category).catch { it ->
                    _meals.value = RemoteStatus.Failure(it)
                }.collect{ it ->
                    _meals.value = RemoteStatus.Success(it)
                }
            } catch (e: Exception) {
                _meals.value = RemoteStatus.Failure(e.fillInStackTrace())
            }
        }
    }

}