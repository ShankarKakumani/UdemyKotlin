package com.shankar.udemykotlin.advanced_Kotlin

import android.util.Log

open class Bicycle {

    var speed: Int  = 0
    var gear: Int = 0
    var wheels: Int = 0


    open fun speedUp() {
        speed+=10
        Log.d("TAG_O1", "Speed is : $speed")
    }
    fun  changeGear() {
        Log.d("TAG_01", "gear")
    }
}

class BicycleBrand: Bicycle() {

    override fun speedUp() {
//        super.speedUp()
        speed+=20
        Log.d("TAG_O1", "Speed in override : $speed")

    }
}