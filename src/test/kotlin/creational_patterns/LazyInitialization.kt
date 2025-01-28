package creational_patterns

import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class AlertBox {
    var message: String? = null

    fun show() {
        println("AlertBox $this: $message")
    }
}

class Window {
    private val box by lazy { AlertBox() }

    fun showMessage(message: String) {
        box.message = message
        box.show()
    }
}

class Windows2 {
    private lateinit var box: AlertBox

    fun showMessage(message: String) {
        box = AlertBox()
        box.message = message
        box.show()
    }
}

class LazyInitializationTest {
    @Test
    fun lazyInitializationTest() {
        val window = Window()
        window.showMessage("Hello window")
        assertNotNull(
            actual = window
        )
    }

    @Test
    fun laInitializationWindow2Test() {
        val window = Windows2()
        window.showMessage("Hello Second window")
        assertNotNull(
            actual = window
        )
    }
}