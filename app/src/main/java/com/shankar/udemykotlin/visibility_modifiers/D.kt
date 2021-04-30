package com.shankar.udemykotlin.visibility_modifiers

class D : B() {

    fun accessAFromD() {
        val instanceA = A()
        println("property A is : ${instanceA.propertyA}")

        instanceA.functionA()
    }
}