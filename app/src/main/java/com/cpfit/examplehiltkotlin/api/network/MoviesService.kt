package com.cpfit.examplehiltkotlin.api.network

import com.cpfit.examplehiltkotlin.api.model.ModelMovies
import retrofit2.Response
import retrofit2.http.GET

interface MoviesService {
    @GET("/departments")
    suspend fun getMoviesList(): Response<List<ModelMovies>>
}