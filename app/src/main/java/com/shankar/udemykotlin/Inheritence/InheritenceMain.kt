package com.shankar.udemykotlin.Inheritence

fun main() {
    val car = CarV(4)
    println("Inherited Properties for car will be doors : ${car.doors} and speed : ${car.speed}")
    println("Inherited Methods for truck car will be move : ${car.move()} and stop : ${car.stop()}")


    val truck = Truck(1000)
    println("Inherited Properties for truck will be doors : ${truck.doors} and speed : ${truck.speed}")
    println("Inherited Methods for truck will be move : ${truck.move()} and stop : ${truck.stop()}")
}