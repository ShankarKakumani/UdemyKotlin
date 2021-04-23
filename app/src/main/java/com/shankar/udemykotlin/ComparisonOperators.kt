package com.shankar.udemykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ComparisonOperators : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comparison_operators)

        val num1: Int = 1
        val num2 : Int = 4

        Log.d("TAG2","num1 < num2 : ${num1 < num2}")
        Log.d("TAG2","num1 > num2 : ${num1 > num2}")


        val num3: Int = 10
        val num4 : Int = 10

        Log.d("TAG2","num3 <= num4 ; ${num3 <= num4}")
        Log.d("TAG2","num3 >= num4 : ${num3 >= num4}")
        Log.d("TAG2","num3 == num4 : ${num3 == num4}")
        Log.d("TAG2","num3 != num4 : ${num3 != num4}")

    }
}