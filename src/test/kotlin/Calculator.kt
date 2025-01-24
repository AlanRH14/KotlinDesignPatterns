
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Calculator {
    fun sum(a: Int, b: Int) = a + b
}

class CalculatorTest {
    @Test
    fun testSum() {
        val calc = Calculator()

        assertEquals(
            expected = 8,
            actual = calc.sum(5, 3)
        )
    }
}