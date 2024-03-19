package com.cpfit.examplehiltkotlin.api.viewModel

import android.content.Context
import android.preference.PreferenceManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cpfit.examplehiltkotlin.api.HostSelectionInterceptor
import com.cpfit.examplehiltkotlin.api.NetworkResult
import com.cpfit.examplehiltkotlin.api.model.ModelMovies
import com.cpfit.examplehiltkotlin.api.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repo: MoviesRepository, val inter : HostSelectionInterceptor, @ApplicationContext context: Context):ViewModel()  {
    val getTodo: LiveData<NetworkResult<List<ModelMovies>>>
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
            repo.getMoviesList()
        }
    }
}