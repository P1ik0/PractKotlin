package com.softserve.edu

fun main() {
    println("Fifteen Puzzle Game!")


    val empty: Byte = 16
    val finalState = ByteArray(empty.toInt()) { (it + 1).toByte() }
    val state: ByteArray = finalState.clone().also { it.shuffle() }

    var inversion = state.indexOf(empty) / 4 + 1
    val last = state.size - 1
    for (i in 0..<last) {
        if (state[i] == empty) continue
        for (j in i + 1..last) {
            if (state[i] > state[j]) inversion++
        }
    }
    println("Inversion: " + inversion)
    if (inversion % 2 == 1) {
        println(" First state: " + state.joinToString(" "))
        state[last] = state[last - 1].also { state[last - 1] = state[last] }
        println(" Update state: " + state.joinToString(" "))
    }

    while (!state.contentEquals(finalState)) {
        println("-".repeat(19))
        for (iRow in 0..3) {
            print("|")
            for (iCol in 0..3) {
                val ix = iRow * 4 + iCol
                val cell = if (state[ix] == empty) "" else state[ix].toString()
                print("%4s".format(cell))
            }
            println(" |")
        }
        println("-".repeat(19))

        print("Enter the cell to move or q to exit: ")
        val response = readln()
        if (response.lowercase() == "q") break
        val cell = response.toByteOrNull() ?: -1
        if (cell !in 1..15) {
            println("It's not a number of some cell on the field: '$response'")
            continue
        }

        val ixBase = state.indexOf(cell)
        val iRowBase = ixBase / 4
        val iColBase = ixBase % 4
        var isMove = false
        for (i in -1..1 step 2){
            val iCol = iColBase + i
            var ix = iRowBase * 4 + iCol
            if (iCol in 0..3 && state[ix] == empty) {
                state[ixBase] = state[ix].also { state[ix] = state[ixBase] }
                isMove = true
                break
            }
            val iRow = iRowBase + i
            ix = iRow * 4 + iColBase
            if (iRow in 0..3 && state[ix] == empty){
                state[ixBase] = state[ix].also { state[ix] = state[ixBase] }
                isMove = true
                break
            }
        }
        if (!isMove) println("This number '$cell' cannot be moved")

        if (state.contentEquals(finalState)){
            println("Congratulation, you win!")
            println("-".repeat(19))
            for (iRow in 0..3){
                print("|")
                for (iCol in 0 ..3){
                    val ix = iRow * 4 + iCol
                    val cell = if (state[ix] == empty) "" else state[ix].toString()
                    print("%4s".format(cell))
                }
                println(" |")
            }
            println("-".repeat(19))
        }
    }
    println("Goodbye!")
}