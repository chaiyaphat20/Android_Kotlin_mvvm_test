package com.cpfit.examplehiltkotlin.api

import android.content.Context
import android.preference.PreferenceManager
import android.preference.PreferenceScreen
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject


class HostSelectionInterceptor @Inject constructor(@ApplicationContext context: Context) : Interceptor {
    private val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context) // ใช้ context ของคุณ

    @Volatile
    private var host: String? = null
    fun setHost(host: String?) {
        this.host = host
    }

    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {
        var request = chain.request()
        val currentHostPref = sharedPrefs.getString("BASE_URL", "jsonplaceholder.typicode.com") // ใช้ค่า host ที่ถูกตั้งค่าในคลาส
        Log.d("BASE_URL",currentHostPref.toString())
        if (currentHostPref != null) {
            val newUrl = request.url.newBuilder()
                .host(currentHostPref)
                .build()
            request = request.newBuilder()
                .url(newUrl)
                .build()
        }
        return chain.proceed(request)
    }
}