package com.cpfit.examplehiltkotlin.network

import com.cpfit.examplehiltkotlin.model.ModelDetail
import com.cpfit.examplehiltkotlin.model.ModelDepartment
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DepartmentService {
    @GET("/departments")
    suspend fun getDepartmentList(): Response<List<ModelDepartment>>

    @GET("departments/{id}/products")
    suspend fun getDepartmentDetailById(@Path("id") id: Int): Response<List<ModelDetail>>
}