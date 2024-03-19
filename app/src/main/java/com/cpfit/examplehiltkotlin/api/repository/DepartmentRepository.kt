package com.cpfit.examplehiltkotlin.api.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cpfit.examplehiltkotlin.api.NetworkResult
import com.cpfit.examplehiltkotlin.api.model.ModelDetail
import com.cpfit.examplehiltkotlin.api.model.ModelDepartment
import com.cpfit.examplehiltkotlin.api.network.DepartmentService
import org.json.JSONObject
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class DepartmentRepository @Inject constructor(private val services: DepartmentService) {
    private val _getMovieLiveData = MutableLiveData<NetworkResult<List<ModelDepartment>>>()
    val getTodoLiveData: LiveData<NetworkResult<List<ModelDepartment>>>
        get() = _getMovieLiveData //getter

    private val _getDepartmentDetailLiveData = MutableLiveData<NetworkResult<List<ModelDetail>>>()
    val getDepartmentDetailLiveData: LiveData<NetworkResult<List<ModelDetail>>>
        get() = _getDepartmentDetailLiveData //getter


    suspend fun getMoviesList() {
        try {
            val response = services.getDepartmentList()
            handleResponseGetMovieList(response)
        } catch (e: Exception) {
            when (e) {
                is SocketTimeoutException -> {
                    val msg = e.toString()
                    _getMovieLiveData.postValue(NetworkResult.Error(msg))
                }

                is UnknownHostException -> {
                    val msg = "ไม่สามารถเชื่อมต่อ VPN"
                    _getMovieLiveData.postValue(NetworkResult.Error(msg))
                }

                else ->
                    _getMovieLiveData.postValue(NetworkResult.Error(e.toString()))
            }
        }
    }

    private fun handleResponseGetMovieList(response: Response<List<ModelDepartment>>) {
        if (response.isSuccessful && response.body() != null) {
            _getMovieLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _getMovieLiveData.postValue(
                NetworkResult.Error(
                    errorObj.get("message").toString()
                )
            )
        } else {
            _getMovieLiveData.postValue(NetworkResult.Error("Some thing went wrong."))
        }
    }


    suspend fun getDepartmentDetailById(id: Int) {
        try {
            val response = services.getDepartmentDetailById(id)
            handleResponseGetDepartmentDetailById(response)
        } catch (e: Exception) {
            when (e) {
                is SocketTimeoutException -> {
                    val msg = e.toString()
                    _getDepartmentDetailLiveData.postValue(NetworkResult.Error(msg))
                }

                is UnknownHostException -> {
                    val msg = "ไม่สามารถเชื่อมต่อ VPN"
                    _getDepartmentDetailLiveData.postValue(NetworkResult.Error(msg))
                }

                else ->
                    _getDepartmentDetailLiveData.postValue(NetworkResult.Error(e.toString()))
            }
        }
    }

    private fun handleResponseGetDepartmentDetailById(response: Response<List<ModelDetail>>) {
        if (response.isSuccessful && response.body() != null) {
            _getDepartmentDetailLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _getDepartmentDetailLiveData.postValue(
                NetworkResult.Error(
                    errorObj.get("message").toString()
                )
            )
        } else {
            _getDepartmentDetailLiveData.postValue(NetworkResult.Error("Some thing went wrong."))
        }
    }
}