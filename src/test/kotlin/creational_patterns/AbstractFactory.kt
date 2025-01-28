package creational_patterns

import org.junit.jupiter.api.Test
import kotlin.test.assertIs

interface DataSource

class DatabaseDataSource : DataSource

class NetworkDataSource : DataSource

abstract class DataSourceFactory {
    abstract fun makeDataSource(): DataSource

    companion object {
        inline fun <reified T : DataSource> createFactory(): DataSourceFactory =
            when (T::class) {
                NetworkDataSource::class -> NetworkFactory()

                DatabaseDataSource::class -> DatabaseFactory()

                else -> throw IllegalArgumentException()
            }
    }
}

class NetworkFactory : DataSourceFactory() {
    override fun makeDataSource(): DataSource = NetworkDataSource()
}

class DatabaseFactory : DataSourceFactory() {
    override fun makeDataSource(): DataSource = DatabaseDataSource()
}

class AbstractFactoryTest {
    @Test
    fun abstractFactoryTest() {
        val databaseDataSourceFactory = DataSourceFactory.createFactory<DatabaseDataSource>()
        val dbDataSource = databaseDataSourceFactory.makeDataSource()
        println("Created datasource: $dbDataSource")

        val networkDataSourceFactory = DataSourceFactory.createFactory<NetworkDataSource>()
        val ntDataSource = networkDataSourceFactory.makeDataSource()

        assertIs<DatabaseDataSource>(dbDataSource)
        assertIs<NetworkDataSource>(ntDataSource)
    }
}