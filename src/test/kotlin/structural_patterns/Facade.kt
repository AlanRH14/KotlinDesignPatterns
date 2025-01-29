package structural_patterns

import kotlin.test.Test
import kotlin.test.assertEquals

class ComplexSystemStore(private val filePath: String) {
    private val cache: HashMap<String, String>

    init {
        println("Reading data from the file $filePath")
        cache = HashMap()

        // Read properties from file and put to cache
    }

    fun store(key: String, value: String) {
        cache[key] = value
    }

    fun read(key: String) = cache[key] ?: ""

    fun commit() = println("Storing cached data to file $filePath")
}

data class User(val login: String)

// Facade
class UserRepository {
    private val systemPreferences = ComplexSystemStore("/data/default.prefs")

    fun save(user: User) {
        systemPreferences.store("USER_KEY", user.login)
        systemPreferences.commit()
    }

    fun findFirst(): User = User(systemPreferences.read(key = "USER_KEY"))
}

class FacadeTest {
    @Test
    fun testFacade() {
        val userRepo = UserRepository()
        val user = User("LordMiau")
        userRepo.save(user = user)

        val retrievedUser = userRepo.findFirst()
        assertEquals(
            actual = retrievedUser.login,
            expected = "LordMiau"
        )
    }
}