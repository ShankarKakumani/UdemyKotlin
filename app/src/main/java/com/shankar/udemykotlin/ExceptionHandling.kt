package com.shankar.udemykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.Exception

class ExceptionHandling : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exception_handling)

        try {
            20 / 0
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}