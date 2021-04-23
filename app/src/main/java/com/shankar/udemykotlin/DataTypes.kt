package com.shankar.udemykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class DataTypes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_types)

        //Declaring int variables
        val a : Int = 10
        val b : Int = 5
        val c : Int = 3

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

        val floatNum1 : Float = 1.4f
        val floatNum2 : Float = 5.6f

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


        /**
         * Array
         */

        val intArray : Array<Int>  = arrayOf(1, 5, 9, 22)
        val stringArray : Array<String>  = arrayOf("Sha", "K", "2", "true   ")

        val mixArray : Array<Any> = arrayOf("Shankar", 9, true, 3.5f, 's')


        Log.d("TAG_ARRAY","int Array  : ${intArray[2]}, ${stringArray[1]}, ${mixArray[3]}")




    }

}