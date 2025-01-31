package behavioural_patterns

import kotlin.test.Test

class Pinter(private val stringFormatterStrategy: (String) -> String) {
    fun printString(message: String) {
        println(stringFormatterStrategy(message))
    }
}

val lowercaseFormatter = { lower: String -> lower.lowercase() }
val uppercaseFormatter = { upper: String -> upper.uppercase() }

class StrategyTest {
    @Test
    fun strategyTest() {
        val inputString = "LOREM ipsum DOLOR sit amet"

        val lowercasePinter = Pinter(lowercaseFormatter)
        lowercasePinter.printString(inputString)

        val uppercasePinter = Pinter(uppercaseFormatter)
        uppercasePinter.printString(inputString)
    }
}