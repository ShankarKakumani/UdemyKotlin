package com.shankar.udemykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ControlFlows : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control_flows)


        /**
         * If, If-else
         */
        val number : Int = 18
        var result : String = ""
        if(number % 2 == 0)
        {
            result = "even"
        }
        else
        {
            result = "odd"
        }
        Log.d("TAG3","Result : $result")


        val myFirstNum : Float = 45.9f
        val mySecondNum : Float = 56.3f


        val max = if(myFirstNum > mySecondNum) {
            myFirstNum
        }
        else{
            mySecondNum
        }


        Log.d("TAG3","Max Number is : $max")

        /**
         * If -- else_if -- else
         */

        val age : Int = 22

        val ageRange = if (age < 12) {
            "Child"
        } else if (age > 12 && age < 17) {
            "Teen"
        } else if (age in 18..22) {
            "Young adult"
        } else if (age in 23..30) {
            "Adult"
        } else if (age in 31..50) {
            "Middle Age"
        } else {
            "Old"
        }

        Log.d("TAG3", "ageRange : $ageRange")


        /**
         * When Expression
         */

        //Example One
        val dayNum : Int = 7
        val dayName = when(dayNum)
        {
            1 -> "Monday"
            2 -> "Tuesday"
            3 -> "Wednesday"
            4 -> "Thursday"
            5 -> "Friday"
            6 -> "Saturday"
            7 -> "Sunday"
            else -> "Invalid Day"
        }

        Log.d("TAG3", "Day of week is : $dayName")

        //Example Two
        val toDo = when(dayNum)
        {
            1, 2, 3, 4, 5 -> "Go to School"
            6, 7 -> "Yahoo, Its Gaming Time!!!"
            else -> "Invalid Day"
        }
        Log.d("TAG3", "What to do today : $toDo")


        //Example Three
        val myNumber : Int = 322

        val numberOfDigits = when (myNumber)
        {
            in 1..9 -> "One digit Number"
            in 10..99 -> "Two digit Number"
            in 100..999 -> "Three digit Number"
            in 1000..9999 -> "Four digit Number"
            in 10000..99999 -> "Five digit Number"
            else -> "Length is More than 5 Digits"
        }

        Log.d("TAG3", "No of Digits : $numberOfDigits")

        //Example Four

        val numberTypeGuess : Any = 5.8f

        val numberTypeData = when(numberTypeGuess)
        {
            is Int -> "Int"
            is Char -> "Char"
            is Float -> "Float"
            is String -> "String"
            is Double -> "Double"
            is Boolean -> "Boolean"
            is Array<*> -> "Array"
            else -> "Invalid Data Type"
        }

        Log.d("TAG3", "Data Type : $numberTypeData")

        /**
         * While
         */

        var increasingNum : Int = 1

        while (increasingNum < 10)
        {

            Log.d("TAG3", "My Increased Number is : $increasingNum")
            increasingNum += 1
//            increasingNum++

        }


        /**
         * Do - While
         *
         * in Do-While, the code inside do{} will execute once at least.
         */

        var iNum : Int = 10

        do {

            iNum++
            Log.d("TAG3", "iNum value is : $iNum")

        }while (iNum < 10)


        /**
         * For Loop
         */

        for(i in 1..6)
        {
            Log.d("TAG3", "For - 1..6 : $i")

        }

        for(i in 6 downTo 1)
        {
            Log.d("TAG3", "For - 6 downTo 1 : $i")

        }

        for(i in 5..20 step 2){
            Log.d("TAG3", "For - 5..20 step 2 : $i")
        }

        //For in Array
        val myArray : Array<String> = arrayOf("Kotlin", "Android", "Q", "Developer", "Pie")

        for(element in myArray)
        {
            Log.d("TAG3", "element in Array : $element")
        }

        for(index in myArray.indices)
        {
            Log.d("TAG3", "myArray[$index] = ${myArray[index]}")

        }

        for((index, element) in myArray.withIndex())
        {
            Log.d("TAG3", "myArray[$index] = $element")

        }


        nullSafety()
    }

    private fun nullSafety() {

        val myName : String = "Shankar"
    }
}