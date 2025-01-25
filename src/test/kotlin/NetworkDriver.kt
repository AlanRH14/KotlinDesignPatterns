import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

object NetworkDriver {
    init {
        println("Initializing: $this")
    }

    fun log(): NetworkDriver = apply { println("Network driver: $this") }
}

class SingletonTest {
    @Test
    fun testSingleton() {
        println("Start")
        val networkDriver1 = NetworkDriver.log()
        val networkDriver2 = NetworkDriver.log()
        assertEquals(
            actual = NetworkDriver,
            expected = networkDriver1
        )
        assertEquals(
            actual = NetworkDriver,
            expected = networkDriver2
        )
    }
}