package com.shankar.udemykotlin.classes

fun main() {

    val car1 = Car(150, 8, 9)
    car1.speedUp()

    println("Car speed : ${car1.speed}")


    val car2 = Car(5, 50, 5, 4)

    println("distance crossed by speed : ${car2.distance }")

}