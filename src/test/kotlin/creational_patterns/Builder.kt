package creational_patterns

import kotlin.test.Test
import kotlin.test.assertEquals

class Component private constructor(builder: Builder) {
    var param1: String? = null
    var param2: Int? = null
    var param3: Boolean? = null

    class Builder {
        private var param1: String? = null
        private var param2: Int? = null
        private var param3: Boolean? = null

        fun setParam1(param1: String) = apply { this.param1 = param1 }
        fun setParam2(param2: Int) = apply { this.param2 = param2 }
        fun setParam3(param3: Boolean) = apply { this.param3 = param3 }
        fun build() = Component(this)

        fun gerParam1() = param1
        fun getParam2() = param2
        fun getParam3() = param3
    }

    init {
        param1 = builder.gerParam1()
        param2 = builder.getParam2()
        param3 = builder.getParam3()
    }
}

class BuilderTest {
    @Test
    fun builderCompleteParamsTest() {
        val component = Component
            .Builder()
            .setParam1("Some Value")
            .setParam2(14)
            .setParam3(true)
            .build()

        assertEquals(
            actual = component.param1,
            expected = "Some Value"
        )
        assertEquals(
            actual = component.param2,
            expected = 14
        )
        assertEquals(
            actual = component.param3,
            expected = true
        )
    }

    @Test
    fun builderWithoutParam2Test() {
        val component = Component.Builder()
            .setParam1("Some Value")
            .setParam3(true)
            .build()

        assertEquals(
            actual = component.param1,
            expected = "Some Value"
        )
        assertEquals(
            actual = component.param2,
            expected = null,
        )
        assertEquals(
            actual = component.param3,
            expected = true
        )
    }

    @Test
    fun builderWithoutParam3Test() {
        val component = Component.Builder()
            .setParam1("Some Value")
            .setParam2(14)
            .build()

        assertEquals(
            actual = component.param1,
            expected = "Some Value"
        )
        assertEquals(
            actual = component.param2,
            expected = 14
        )
        assertEquals(
            actual = component.param3,
            expected = null
        )
    }
}