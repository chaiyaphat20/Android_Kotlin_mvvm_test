package com.cpfit.examplehiltkotlin.api.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cpfit.examplehiltkotlin.api.NetworkResult
import com.cpfit.examplehiltkotlin.api.model.ToDORes
import com.cpfit.examplehiltkotlin.api.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val repo: TodoRepository):ViewModel()  {
    val getTodo: LiveData<NetworkResult<ToDORes>>
        get() = repo.getTodoLiveData
    //Pattern

    fun getTodo() {
        viewModelScope.launch {
            repo.getTodo()
        }
    }
}