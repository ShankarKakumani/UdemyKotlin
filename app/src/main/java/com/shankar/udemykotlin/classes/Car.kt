package com.shankar.udemykotlin.classes

class Car(val speed: Int, val gear: Int, val seat: Int) {


    fun speedUp(): Int {
        speed * 5
        return speed
    }

    var distance : Int = 0

    constructor(time: Int, speed: Int, gear: Int, seat: Int) : this(speed, gear, seat) {
        distance = speed * time
    }
}