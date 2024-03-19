package com.cpfit.examplehiltkotlin.api.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cpfit.examplehiltkotlin.api.NetworkResult
import com.cpfit.examplehiltkotlin.api.model.ModelMovies
import com.cpfit.examplehiltkotlin.api.network.MoviesService
import org.json.JSONObject
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val services: MoviesService) {
    private val _getMovieLiveData = MutableLiveData<NetworkResult<List<ModelMovies>>>()
    val getTodoLiveData: LiveData<NetworkResult<List<ModelMovies>>>
        get() = _getMovieLiveData //getter

    suspend fun getMoviesList() {
        try {
            val response = services.getMoviesList()
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

    private fun handleResponseGetMovieList(response: Response<List<ModelMovies>>) {
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
}