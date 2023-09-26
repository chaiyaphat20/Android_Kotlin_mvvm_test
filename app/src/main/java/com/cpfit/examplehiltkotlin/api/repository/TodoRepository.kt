package com.cpfit.examplehiltkotlin.api.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cpfit.examplehiltkotlin.api.NetworkResult
import com.cpfit.examplehiltkotlin.api.model.ToDORes
import com.cpfit.examplehiltkotlin.api.network.TodoService
import org.json.JSONObject
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class TodoRepository @Inject constructor(private val services: TodoService)  {
    //checkCart
    private val _getTodoLiveData = MutableLiveData<NetworkResult<ToDORes>>()
    val getTodoLiveData: LiveData<NetworkResult<ToDORes>>
        get() = _getTodoLiveData //getter

    suspend fun getTodo() {
        try {
            val response = services.getTodo1()
            handleResponseTodo(response)
        } catch (e: Exception) {
            when (e) {
                is SocketTimeoutException -> {
                    val msg = e.toString()
                    _getTodoLiveData.postValue(NetworkResult.Error(msg))
                }
                is UnknownHostException -> {
                    val msg = "ไม่สามารถเชื่อมต่อ VPN"
                    _getTodoLiveData.postValue(NetworkResult.Error(msg))
                }
                else ->
                    _getTodoLiveData.postValue(NetworkResult.Error(e.toString()))
            }
        }
    }

    private fun handleResponseTodo(response: Response<ToDORes>) {
        if (response.isSuccessful && response.body() != null) {
            _getTodoLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _getTodoLiveData.postValue(
                NetworkResult.Error(
                    errorObj.get("message").toString()
                ))
        } else {
            _getTodoLiveData.postValue(NetworkResult.Error("Some thing went wrong."))
        }
    }
}