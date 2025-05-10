package com.softserve.edu

enum class BonusLevel{
    NO_BONUS,
    LOW_BONUS,
    MEDIUM_BONUS,
    HIGH_BONUS
}

fun calculateBonusLevel(appleCount: Int):BonusLevel{
    return when{
        appleCount < 100 -> BonusLevel.NO_BONUS
        appleCount in 100..199 -> BonusLevel.LOW_BONUS
        appleCount in 200..299 -> BonusLevel.MEDIUM_BONUS
        else -> BonusLevel.HIGH_BONUS
    }
}

fun getBonusMessage(workerName: String, appleCount: Int){
    val bonusLevel = calculateBonusLevel(appleCount)
    val bonusMessage= when (bonusLevel){
        BonusLevel.NO_BONUS -> "0 bonus"
        BonusLevel.LOW_BONUS -> "small bonus"
        BonusLevel.MEDIUM_BONUS -> "middle bonus"
        BonusLevel.HIGH_BONUS -> "high bonus"
    }
    println("Worker $workerName collect $appleCount apples abd got $bonusMessage.")
}

fun main(){
    getBonusMessage("Alex", 50)
    getBonusMessage("Artem", 150)
    getBonusMessage("Max", 250)
    getBonusMessage("Ivan", 350)
}