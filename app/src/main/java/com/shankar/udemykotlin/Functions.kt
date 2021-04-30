package com.shankar.udemykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class Functions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_functions2)


        myFirstSimpleFunction()

        val day = dayOfWeek()
        Log.d("TAG_FUNC", "My Favorite day is : $day")

        val number1 = 29
        val number2 = 33
        val sumResult = sum(number1, number2)

        Log.d("TAG_FUNC", "Sum of $number1 & $number2 is : $sumResult")
    }


    private fun myFirstSimpleFunction() {
        Log.d("TAG_FUNC", "I am inside myFirstSimpleFunction")
    }


    private fun dayOfWeek(): String {

        val arrayOfDays =
            arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

        return arrayOfDays[(0..6).random()]
    }

    fun sum(number1: Int, number2: Int): Int {

        return number1 + number2
    }

    //Inline return
    //fun sum(number1: Int, number2: Int) = number1 + number2
}