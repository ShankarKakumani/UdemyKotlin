package com.shankar.udemykotlin.advanced_Kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.shankar.udemykotlin.R

class AdvancedKotlin : AppCompatActivity() {


    companion object {
        /**
         * Const Val is Initialized in Compile Time
         */
        const val REQUEST_CODE = 1
        //String & Primitives Data Types only
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advanced_kotlin)


        /**
         * Enum Usage
         */

        val level = EnumCourse.ADVANCED.type
        Log.d("TAG_ENUM", level)


        /**
         * Overriding a Function
         */

        val b1 = BicycleBrand()
        b1.speedUp()


    }


}