package structural_patterns

import kotlin.test.Test

interface CoffeeMachine {
    fun makeSmallCoffee()
    fun makeLargeCoffee()
}

class NormalCoffeeMachine : CoffeeMachine {
    override fun makeSmallCoffee() {
        println("Normal Coffee Machine: Making small coffee")
    }

    override fun makeLargeCoffee() {
        println("Normal Coffee Machine: Making large coffee")
    }
}

class EnhancedCoffeeMachine(private val coffeeMachine: CoffeeMachine) : CoffeeMachine by coffeeMachine {
    override fun makeLargeCoffee() {
        println("Enhanced Coffee Machine: Making large coffee")
    }

    fun makMilkCoffee() {
        println("Enhanced coffee machine: Making milk coffee")
        coffeeMachine.makeSmallCoffee()
        println("Enhanced coffee machine: Adding milk")
    }
}

class DecoratorTest {
    @Test
    fun decoratorTest() {
        val normalMachine = NormalCoffeeMachine()
        val enhancedMachine = EnhancedCoffeeMachine(normalMachine)

        enhancedMachine.makeSmallCoffee()
        println("---------------------------")
        enhancedMachine.makeLargeCoffee()
        println("---------------------------")
        enhancedMachine.makMilkCoffee()
    }
}