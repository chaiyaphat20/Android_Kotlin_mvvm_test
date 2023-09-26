package com.cpfit.examplehiltkotlin.api.viewModel

import android.content.Context
import android.preference.PreferenceManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cpfit.examplehiltkotlin.api.HostSelectionInterceptor
import com.cpfit.examplehiltkotlin.api.NetworkResult
import com.cpfit.examplehiltkotlin.api.model.ToDORes
import com.cpfit.examplehiltkotlin.api.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val repo: TodoRepository,val inter : HostSelectionInterceptor,@ApplicationContext context: Context):ViewModel()  {
    val getTodo: LiveData<NetworkResult<ToDORes>>
        get() = repo.getTodoLiveData
    //Pattern
    val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context) // ใช้ context ของคุณ
    fun setUrl(url:String){
        val editor = sharedPrefs.edit()
        editor.putString("BASE_URL", url)
        editor.apply()
    }

    fun clearUrl(){
        val editor = sharedPrefs.edit()
        editor.putString("BASE_URL", null)
        editor.apply()
    }

    fun getTodo() {
        viewModelScope.launch {
            repo.getTodo()
        }
    }
}