package com.shankar.udemykotlin.visibility_modifiers

class C : A() {

    fun accessClassA() {
        val instanceA = A()
        instanceA.propertyA
        println("property A is : ${instanceA.propertyA}")

        instanceA.functionA()
    }
}