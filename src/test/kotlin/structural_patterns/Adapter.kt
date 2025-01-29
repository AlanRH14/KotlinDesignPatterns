package structural_patterns

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
        list.add(DatabaseData(position =  3, amount = 7))
        list.add(DatabaseData(position = 4, amount = 23))
        return list
    }
}

