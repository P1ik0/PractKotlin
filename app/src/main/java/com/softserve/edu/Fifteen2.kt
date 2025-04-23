package com.softserve.edu

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

fun initState(maxValue: Byte) = ByteArray(maxValue.toInt()) {(it + 1).toByte()}

fun shuffleState(currentState: ByteArray) = currentState.clone().also { it.shuffle() }

fun isOddInversion(currentState: ByteArray, rowSize: Byte): Boolean {
    val maxValue: Byte = (rowSize * rowSize).toByte()
    var inversion = currentState.indexOf(maxValue) / rowSize + 1
    val last = currentState.size - 1
    for (i in 0..last){
        if (currentState[i] == maxValue) continue
        for (j in i + 1..last){
            if (currentState[i] > currentState[j]) inversion++
        }
    }
    println("Inversion = " + inversion)
    return inversion % 2 == 1;
}

fun updateInversion(currentState: ByteArray){
    val last = currentState.size - 1
    currentState[last] = currentState[last -1 ].also { currentState[last - 1] = currentState[last] }
}

fun show(currentState: ByteArray, rowSize: Byte){
    val empty: Byte = (rowSize * rowSize).toByte()
    println("-".repeat(empty + 3))
    for (iRow in 0..<rowSize) {
        print("|")
        for (iCol in 0..<rowSize){
            val ix = iRow * rowSize + iCol
            val cell = if (currentState[ix] == empty) "" else currentState[ix].toString()
            print("%4s".format(cell))
        }
        println(" |")
    }
    println("-".repeat(empty+3))
}

fun readNumber(rowSize: Byte): Byte {
//fun readNumber(rowSize: Byte, inputStream: InputStream): Byte {
    val maxValue: Byte = (rowSize * rowSize).toByte()
    print("Enter the cell to move or q/0 to exit:")
    var response = readln()
//    var response = BufferedReader(InputStreamReader(inputStream)).readLine()
    if (response.lowercase() == "q") response = "0"
    val cell = response.toByteOrNull() ?: -1
    if (cell !in 0..<maxValue) {
        println("It's not a number of some cell on the field: '$response'")
    }
    return cell
}

fun showMessage(message: String){
    println(message)
}

fun updateState(currentState: ByteArray, cell: Byte, rowSize: Byte): Boolean {
    val empty: Byte = (rowSize * rowSize).toByte()
    val ixBase = currentState.indexOf(cell)
    val iRowBase = ixBase / rowSize
    val iColBase = ixBase % rowSize
    var isMove = false
    for (i in -1..1 step 2){
        val iCol = iColBase + i
        var ix = iRowBase* rowSize + iCol
        if (iCol in 0..<rowSize && currentState[ix] == empty) {
            currentState[ixBase] = currentState[ix].also { currentState[ix] = currentState[ixBase] }
            isMove = true
            break
        }
        val iRow = iRowBase + i
        ix = iRow * 4 + iColBase
        if (iRow in 0..<rowSize && currentState[ix] == empty){
            currentState[ixBase] = currentState[ix].also { currentState[ix] = currentState[ixBase] }
            isMove = true
            break
        }
    }
    return isMove
}


fun main() {
    showMessage("Fifteen Puzzle Game!")

    val rowSize: Byte = 4
    val finalState = initState((rowSize*rowSize).toByte())
    val state: ByteArray = shuffleState(finalState)

    if (isOddInversion(state, rowSize)){
        updateInversion(state)
    }

    while (!state.contentEquals(finalState)){
        show(state, rowSize)
//        val inputStream = "q".byteInputStream()
//        val cell = readNumber(rowSize, inputStream)
        val cell = readNumber(rowSize)
        if (cell == 0.toByte()) break
        if (!updateState(state, cell, rowSize)) showMessage("This number '$cell' cannot be moved")
    }

    if (state.contentEquals(finalState)){
        showMessage("Congratulation, you win!")
        show(state, rowSize)
    }
    showMessage("Goodbye!")
}