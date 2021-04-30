package com.shankar.udemykotlin.visibility_modifiers

open class B {

    fun accessClassAFromB() {
        val instanceA = A()
        println("property A is : ${instanceA.propertyA}")

        instanceA.functionA()
    }
}