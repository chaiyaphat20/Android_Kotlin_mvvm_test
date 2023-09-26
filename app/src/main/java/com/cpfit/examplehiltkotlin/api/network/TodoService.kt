package com.cpfit.examplehiltkotlin.api.network

import com.cpfit.examplehiltkotlin.api.model.ToDORes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface TodoService {
    @GET(".")
    suspend fun getTodo1(): Response<ToDORes>
}