package com.example.food.ui.category.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food.data.remote.entity.Category
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
class CategoryViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var categories = MutableStateFlow<RemoteStatus<List<Category>>>(RemoteStatus.Loading)

    init {
        getCategory()
    }

    private fun getCategory(){
        viewModelScope.launch {
            try {
                repository.getCategories().catch { it ->
                    categories.value = RemoteStatus.Failure(it)
                }.collectLatest { it ->
                    categories.value = RemoteStatus.Success(it)
                }
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }
}