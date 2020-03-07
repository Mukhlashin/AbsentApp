package com.example.absentexample

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking

class BaseApp : Application() {
    companion object{
        val BASE_URL = "http://10.121.3.174"
    }

    override fun onCreate() {
        super.onCreate()
        AndroidNetworking.initialize(applicationContext)
    }
}