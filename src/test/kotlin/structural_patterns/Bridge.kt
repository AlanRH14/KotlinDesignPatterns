package structural_patterns

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

interface Device {
    var volume: Int
    fun getName(): String
}

class Radio : Device {
    override var volume = 0

    override fun getName() = "Radio $this"
}

class TV : Device {
    override var volume = 0

    override fun getName() = "TV $this"
}

interface Remote {
    fun volumeUp()
    fun volumeDown()
}

class BasicRemote(val device: Device) : Remote {
    override fun volumeUp() {
        device.volume++
        println("${device.getName()} volume up: ${device.volume}")
    }

    override fun volumeDown() {
        device.volume--
        println("${device.getName()} volume down: ${device.volume}")
    }
}

class BridgeTest {
    @Test
    fun bridgeTest() {
        val tv = TV()
        val radio = Radio()

        val tvRemote = BasicRemote(tv)
        val radioRemote = BasicRemote(radio)

        tvRemote.volumeUp()
        tvRemote.volumeUp()
        tvRemote.volumeDown()

        radioRemote.volumeUp()
        radioRemote.volumeUp()
        radioRemote.volumeUp()
        radioRemote.volumeDown()

        assertEquals(
            actual = tv.volume,
            expected = 1
        )
        assertEquals(
            actual = radio.volume,
            expected = 2
        )
    }
}
