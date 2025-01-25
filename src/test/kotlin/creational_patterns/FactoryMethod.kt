package creational_patterns

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

sealed class County {
    data object Canada : County()
}

data object Spain : County()
class Greece(val someProperty: String) : County()
data class USA(val someProperty: String) : County()

class Currency(val code: String)

data object CurrencyFactory {
    fun currencyFactory(country: County): Currency =
        when (country) {
            is Spain -> Currency(code = "EUR")
            is Greece -> Currency(code = "EUR")
            is USA -> Currency(code = "USD")
            is County.Canada -> Currency(code = "CAD")
        }
}

class FactoryMethodTest {
    @Test
    fun currencyTest() {
        val greekCurrency = CurrencyFactory.currencyFactory(country = Greece(someProperty = "")).code
        println("Greek Currency: $greekCurrency")
        val usaCurrency = CurrencyFactory.currencyFactory(country = USA(someProperty = "")).code
        println("USA Currency: $usaCurrency")
        assertEquals(
            actual = greekCurrency,
            expected = "EUR"
        )
        assertEquals(
            actual = usaCurrency,
            expected = "USD"
        )
    }
}