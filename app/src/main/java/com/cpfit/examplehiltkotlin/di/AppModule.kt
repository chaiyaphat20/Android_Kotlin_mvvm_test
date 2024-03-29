package com.cpfit.examplehiltkotlin.di

import com.cpfit.examplehiltkotlin.utils.HostSelectionInterceptor
import com.cpfit.examplehiltkotlin.network.DepartmentService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, hostSelectionInterceptor : HostSelectionInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .connectTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.MINUTES)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(hostSelectionInterceptor)
            .build()
    //--//

    val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    //------------------URL1
    @Provides
    @Singleton
    @Named("URL1")
    fun getRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://659f86b15023b02bfe89c737.mockapi.io/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }
    //------------------URL1

    @Provides
    @Singleton
    fun movies(@Named("URL1") retrofit: Retrofit): DepartmentService {
        return retrofit.create(DepartmentService::class.java)
    }
}