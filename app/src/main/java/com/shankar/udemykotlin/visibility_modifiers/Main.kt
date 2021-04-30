package com.shankar.udemykotlin.visibility_modifiers

fun main() {

    val instanceC = C()
    instanceC.accessClassA()

    val instanceB = B()
    instanceB.accessClassAFromB()

    val instanceD = D()
    instanceD.accessAFromD()


    /**
     * Public - Can access from all the classes
     * Private - within the scope
     * Protected - within the scope plus subClass -> { ClassC : ClassA() }
     */
}