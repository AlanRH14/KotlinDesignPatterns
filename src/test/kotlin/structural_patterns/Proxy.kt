package structural_patterns

import org.junit.jupiter.api.Test

interface Image {
    fun display()
}

class RealImage(private val fileName: String): Image {
    override fun display() {
        println("RealImage: Displaying $fileName")
    }

    private fun loadFromDisk(fileName: String) {
        println("RealImage: Loading $fileName")
    }

    init {
        loadFromDisk(fileName)
    }
}

class ProxyImage(private val fileName: String): Image {
    private var realImage: RealImage? = null

    override fun display() {
        println("ProxyImage: Displaying $fileName")
        if (realImage == null) {
            realImage = RealImage(fileName = fileName)
        }

        realImage!!.display()
    }
}

class ProxyTest {
    @Test
    fun proxyTest() {
        val image = ProxyImage(fileName = "test.jpg")

        // Load Image form disk
        image.display()
        println("---------------------------------")

        // Load Image from disk
        image.display()
    }
}