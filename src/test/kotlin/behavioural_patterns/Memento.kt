package behavioural_patterns

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

data class Memento(val state: String)

class Originator(var state: String) {
    fun createMemento() = Memento(state = state)

    fun restoreMemento(memento: Memento) {
        state = memento.state
    }
}

class CareTaker {
    private val mementoList = arrayListOf<Memento>()

    fun saveState(state: Memento) {
        mementoList.add(state)
    }

    fun restore(index: Int) = mementoList[index]
}

class MementoTest {
    @Test
    fun mementoTest() {
        val originator = Originator(state = "Initial state")
        val careTaker = CareTaker()
        careTaker.saveState(originator.createMemento())
        println("Current state is ${originator.state}")

        originator.state = "State 1"
        careTaker.saveState(originator.createMemento())
        println("Current state is ${originator.state}")

        originator.state = "State 2"
        careTaker.saveState(originator.createMemento())
        println("Current state is ${originator.state}")

        assertEquals(
            actual = originator.state,
            expected = "State 2"
        )

        originator.restoreMemento(careTaker.restore(index = 1))
        println("Current state is ${originator.state}")
        assertEquals(
            actual = originator.state,
            expected = "State 1"
        )

        originator.restoreMemento(careTaker.restore(index = 0))
        println("Current state is ${originator.state}")
        assertEquals(
            actual = originator.state,
            expected = "Initial state"
        )

        originator.restoreMemento(careTaker.restore(index = 2))
        println("Current state is ${originator.state}")
        assertEquals(
            actual = originator.state,
            expected = "State 2"
        )
    }
}