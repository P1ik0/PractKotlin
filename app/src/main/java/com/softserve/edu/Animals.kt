package com.softserve.edu

abstract class Animal(val name: String) {
    abstract fun makeSound():String
    abstract fun move():String
}

interface Pet{
    fun play(): String
    fun feed(): String
}

sealed class AnimalType{
    class Mammal: AnimalType()
    class Bird: AnimalType()
    class Fish: AnimalType()
}

class Dog(name: String): Animal(name), Pet{
    override fun makeSound() = "Wouf"

    override fun move() = "Run"

    override fun play() = "Fetch the ball"

    override fun feed() = "Eating dog food"
}

class Cat(name: String): Animal(name), Pet{
    override fun makeSound() = "Meow"

    override fun move() = "Walk"

    override fun play() = "Play with toys"

    override fun feed() = "Eating cat food"
}

class Parrot(name: String): Animal(name), Pet{
    override fun makeSound() = "Speak"

    override fun move() = "Fly"

    override fun play() = "Ring the bell"

    override fun feed() = "Eating parrot food"
}


class Goldfish(name: String): Animal(name){
    override fun makeSound() = "Blup"

    override fun move() = "Swim"
}

fun main(){
    val dog = Dog("Buddy")
    val cat = Cat("Tom")
    val parrot = Parrot("Cringe")
    val goldfish = Goldfish("Goldy")

    val animals: List<Animal> = listOf(dog,cat,parrot,goldfish)

    animals.forEach { animal ->
        println("${animal.name} says: ${animal.makeSound()} and moves by: ${animal.move()}")
        if (animal is Pet) {
            println("${animal.name} likes to: ${animal.play()} and eats: ${animal.feed()}")
        }
        println()
    }

}

