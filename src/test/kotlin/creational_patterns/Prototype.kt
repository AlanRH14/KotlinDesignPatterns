package creational_patterns

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

abstract class Shape : Cloneable {
    var id: String? = null
    var type: String? = null

    abstract fun draw()

    public override fun clone(): Any {
        var clone: Any? = null
        try {
            clone = super.clone()
        } catch (e: CloneNotSupportedException) {
            e.printStackTrace()
        }

        return clone!!
    }
}

class Rectangle : Shape() {
    override fun draw() {
        println("Inside Rectangle::draw() method.")
    }

    init {
        type = "Rectangle"
    }
}

class Square : Shape() {
    override fun draw() {
        println("Inside Square::draw() method.")
    }

    init {
        type = "Square"
    }
}

class Circle : Shape() {
    override fun draw() {
        println("Inside Circle::draw method")
    }

    init {
        type = "Circle"
    }
}

object ShapeCache {
    private val shapeMap = hashMapOf<String?, Shape>()

    fun loadCache() {
        val circle = Circle()
        val square = Square()
        val rectangle = Rectangle()

        shapeMap["1"] = circle
        shapeMap["2"] = square
        shapeMap["3"] = rectangle
    }

    fun getShape(shapeId: String): Shape {
        val cacheShape = shapeMap[shapeId]
        return cacheShape?.clone() as Shape
    }
}

class PrototypeTest {
    @Test
    fun prototypeTest() {
        ShapeCache.loadCache()
        val clonedShape1 = ShapeCache.getShape("1")
        val clonedShape2 = ShapeCache.getShape("2")
        val clonedShape3 = ShapeCache.getShape("3")

        clonedShape1.draw()
        clonedShape2.draw()
        clonedShape3.draw()

        assertEquals(
            actual = clonedShape1.type,
            expected = "Circle"
        )
        assertEquals(
            actual = clonedShape2.type,
            expected = "Square"
        )
        assertEquals(
            actual = clonedShape3.type,
            expected = "Rectangle"
        )
    }
}