package com.shankar.udemykotlin.nested_class

class Outer {

    var a: Int = 10
    inner class Nested {

        fun nestedFunction() {
            println("I am inside Nested Class $a")
        }
    }
}