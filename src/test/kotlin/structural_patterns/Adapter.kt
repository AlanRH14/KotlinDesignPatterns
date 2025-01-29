package structural_patterns

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

// 3rd party functionality

data class DisplayDataType(
    val index: Float,
    val data: String
)

class DataDisplay {
    fun displayData(data: DisplayDataType) {
        println("Data is displayed: ${data.index} - ${data.data}")
    }
}

// Our code

data class DatabaseData(
    val position: Int,
    val amount: Int
)

class DatabaseGenerator {
    fun generatorData(): List<DatabaseData> {
        val list = arrayListOf<DatabaseData>()
        list.add(DatabaseData(position = 2, amount = 2))
        list.add(DatabaseData(position = 3, amount = 7))
        list.add(DatabaseData(position = 4, amount = 23))
        return list
    }
}

interface DatabaseConverter {
    fun convertData(data: List<DatabaseData>): List<DisplayDataType>
}

class DataDisplayAdapter(val display: DataDisplay) : DatabaseConverter {
    override fun convertData(data: List<DatabaseData>): List<DisplayDataType> {
        val returnList = arrayListOf<DisplayDataType>()
        for (datum in data) {
            val ddt = DisplayDataType(
                index = datum.position.toFloat(),
                data = datum.amount.toString()
            )
            display.displayData(ddt)
            returnList.add(ddt)
        }
        return returnList
    }
}

class AdapterTest {
    @Test
    fun adapterListSizeTest() {
        val generator = DatabaseGenerator()
        val generatedData = generator.generatorData()
        val adapter = DataDisplayAdapter(display = DataDisplay())
        val convertData = adapter.convertData(generatedData)

        assertEquals(
            actual = convertData.size,
            expected = 3
        )
    }

    @Test
    fun adapterItem0Test() {
        val generator = DatabaseGenerator()
        val generatedData = generator.generatorData()
        val adapter = DataDisplayAdapter(DataDisplay())
        val convertData = adapter.convertData(generatedData)

        assertEquals(
            actual = convertData[0],
            expected = DisplayDataType(2F, "2")
        )
    }

    @Test
    fun adapterItem1Test() {
        val generator = DatabaseGenerator()
        val generatedData = generator.generatorData()
        val adapter = DataDisplayAdapter(DataDisplay())
        val convertData = adapter.convertData(generatedData)

        assertEquals(
            actual = convertData[1],
            expected = DisplayDataType(3F, "7")
        )
    }

    @Test
    fun adapterItem2Test() {
        val generator = DatabaseGenerator()
        val generatedData = generator.generatorData()
        val adapter = DataDisplayAdapter(DataDisplay())
        val convertData = adapter.convertData(generatedData)

        assertEquals(
            actual = convertData[2],
            expected = DisplayDataType(4F, "23")
        )
    }
}

