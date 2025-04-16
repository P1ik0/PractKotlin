package com.softserve.edu
import com.google.gson.Gson

data class MyModel(
    val id: Int,
    val description: String
)

fun main() {
    println("Simple Task. JSON")
//
    val gson = Gson()
    val jsonString = gson.toJson(MyModel(1,"Test"))
    println(jsonString)
}