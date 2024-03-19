package com.cpfit.examplehiltkotlin.viewModel

import android.content.Context
import android.preference.PreferenceManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cpfit.examplehiltkotlin.utils.HostSelectionInterceptor
import com.cpfit.examplehiltkotlin.utils.NetworkResult
import com.cpfit.examplehiltkotlin.model.ModelDetail
import com.cpfit.examplehiltkotlin.model.ModelDepartment
import com.cpfit.examplehiltkotlin.repository.DepartmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepartmentViewModel @Inject constructor(
    private val repo: DepartmentRepository,
    val inter: HostSelectionInterceptor,
    @ApplicationContext context: Context
) : ViewModel() {
    val getTodo: LiveData<NetworkResult<List<ModelDepartment>>>
        get() = repo.getTodoLiveData

    val getDepartmentDetail: LiveData<NetworkResult<List<ModelDetail>>>
        get() = repo.getDepartmentDetailLiveData

    //Pattern
    val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context) // ใช้ context ของคุณ
    fun setUrl(url: String) {
        val editor = sharedPrefs.edit()
        editor.putString("BASE_URL", url)
        editor.apply()
    }

    fun clearUrl() {
        val editor = sharedPrefs.edit()
        editor.putString("BASE_URL", null)
        editor.apply()
    }

    fun getTodo() {
        viewModelScope.launch {
            repo.getMoviesList()
        }
    }

    fun getDepartmentDetailById(id: Int) {
        viewModelScope.launch {
            repo.getDepartmentDetailById(id)
        }
    }
}