package com.cpfit.examplehiltkotlin.api.network

import com.cpfit.examplehiltkotlin.api.model.ModelDetail
import com.cpfit.examplehiltkotlin.api.model.ModelMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesService {
    @GET("/departments")
    suspend fun getMoviesList(): Response<List<ModelMovies>>

    @GET("departments/{id}/products")
    suspend fun getDepartmentDetailById(@Path("id") id: Int): Response<List<ModelDetail>>
}