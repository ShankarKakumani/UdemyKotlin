package com.shankar.udemykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class DataTypes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_types)

        //Declaring int variables
        var a : Int = 10
        var b : Int = 5
        var c : Int = 3

        //Mathematical operations
        var result : Int = a + b
        Log.d("TAG_MATH","a + b : $result")

        result = a - b
        Log.d("TAG_MATH","a - b : $result")

        result = a * b
        Log.d("TAG_MATH","a * b : $result")

        result = a/b
        Log.d("TAG_MATH","a/b : $result")

        result = a % b
        Log.d("TAG_MATH","a % b (remainder): $result")

        result = a % c
        Log.d("TAG_MATH","a % c (remainder): $result")


        /**
         * Float
         **/

        var floatNum1 : Float = 1.4f
        var floatNum2 : Float = 5.6f

        result = (floatNum1 + floatNum2).toInt()
        Log.d("TAG_MATH","floatNum1 + floatNum2 : $result")


        /**
         * Char
         */

        val myChar : Char = 'a'
        val myChar1 : Char = '2'

        /**
         * Strings
         */

        val string1 : String = "Shankar"
        val string2 : String = "Kakumani"

        val string3 : String = string1 + string2
        val string4 : String = "$string1$string2"

        Log.d("TAG_STR","string3  : $string3")
        Log.d("TAG_STR","string4  : $string4")


    }

}