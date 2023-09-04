package com.example.food.ui.category.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Category
import com.example.domain.repo.Repository
import com.example.domain.usecase.UseCase
import com.example.food.utils.RemoteStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val useCase: UseCase) : ViewModel() {

    private val _categories = MutableStateFlow<RemoteStatus<List<Category>>>(RemoteStatus.Loading)
    val categories : StateFlow<RemoteStatus<List<Category>>> = _categories
    init {
        getCategory()
    }

    private fun getCategory(){
        viewModelScope.launch {
            try {
                useCase.getCategories().catch {
                    _categories.value = RemoteStatus.Failure(it)
                }.collectLatest {
                    _categories.value = RemoteStatus.Success(it)
                }
            }catch (e : Exception){
                _categories.value = RemoteStatus.Failure(e.fillInStackTrace())
            }
        }
    }
}