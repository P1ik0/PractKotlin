package com.softserve.edu

import org.junit.Assert.*

import org.junit.Test

class Fifteen2Test {

    @Test
    fun initState() {
        val expectedState: ByteArray =
            byteArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)

        val actualState = initState(16)

        assertArrayEquals(expectedState, actualState)
    }

    @Test
    fun shuffleState() {
        val finalState: ByteArray =
            byteArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        val actualState = shuffleState(finalState)

        assertFalse(finalState.equals(actualState))
    }

    @Test
    fun isOddInversion() {
        val oddInversionState: ByteArray =
            byteArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 16, 13, 15, 14)

        val inversionState = isOddInversion(oddInversionState, 4)

        assertTrue(inversionState)
    }

    @Test
    fun updateInversion() {
        val expectedState: ByteArray =
            byteArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 16, 13, 14, 15)

        val actualState: ByteArray =
            byteArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 16, 13, 15, 14)

        updateInversion(actualState)

        assertArrayEquals(expectedState, actualState)

    }

    @Test
    fun show() {
        val finalState: ByteArray =
            byteArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)

        show(finalState, 4)
    }

//    @Test
//    fun readNumber4() {
//        println("\nType 4")
//        val inputStream = "4".byteInputStream()
//        assertTrue(readNumber(4, inputStream) in 1..15)
//    }
//
//    @Test
//    fun readNumber16() {
//        println("\nType 16")
//        val inputStream = "16".byteInputStream()
//        assertFalse(readNumber(4, inputStream) in 1..15)
//    }
//
//    @Test
//    fun readNumberB() {
//        println("\nType b")
//        val inputStream = "b".byteInputStream()
//        assertTrue(readNumber(4, inputStream) == (-1).toByte())
//    }
//
//    @Test
//    fun readNumberQ() {
//        println("\nType q")
//        val inputStream = "q".byteInputStream()
//        assertTrue(readNumber(4, inputStream) == 0.toByte())
//    }

    @Test
    fun showMessage() {
        val finalState = "*** message"
        showMessage(finalState)
    }

    @Test
    fun updateState1horizontal() {
        val expectedState: ByteArray =
            byteArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 16, 14, 15)
        val actualState: ByteArray =
            byteArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 16, 13, 14, 15)

        updateState(actualState, 13, 4)
        assertArrayEquals(expectedState, actualState)
    }

    @Test
    fun updateState2vertical() {
        val expectedState: ByteArray =
            byteArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 16, 10, 11, 12, 9, 13, 14, 15)
        val actualState: ByteArray =
            byteArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 16, 13, 14, 15)

        updateState(actualState, 9, 4)
        assertArrayEquals(expectedState, actualState)
    }
}