package structural_patterns

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

open class Equipment(
    open val price: Int,
    val name: String,
)

open class Composite(name: String) : Equipment(price = 0, name = name) {
    private val equipments = ArrayList<Equipment>()

    override val price: Int
        get() = equipments.sumOf { equip -> equip.price }

    fun add(equipment: Equipment) = apply { equipments.add(equipment) }
}

class Computer : Composite(name = "PC")
class Processor : Equipment(price = 1000, name = "Processor")
class HardDrive : Equipment(price = 250, name = "Hard Drive")
class Memory : Composite(name = "Memory")
class ROOM : Equipment(price = 100, name = "Read Only Memory")
class RAM : Equipment(price = 75, name = "Random Access Memory")

class CompositeTest {
    @Test
    fun compositeTest() {
        val memory = Memory()
            .add(ROOM())
            .add(RAM())
        val pc = Computer()
            .add(Processor())
            .add(HardDrive())
            .add(memory)

        println("PC price: ${pc.price}")

        assertEquals(
            actual = pc.name,
            expected = "PC"
        )
        assertEquals(
            actual = pc.price,
            expected = 1425
        )
    }
}