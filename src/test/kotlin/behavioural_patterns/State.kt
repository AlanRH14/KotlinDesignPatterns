package behavioural_patterns

import kotlin.test.Test
import kotlin.test.assertEquals

sealed class AuthorizationState

data object Unauthorized : AuthorizationState()

class Authorized(val userName: String) : AuthorizationState()

class AuthorizedPresenter {
    private var state: AuthorizationState = Unauthorized

    val isAuthorized: Boolean
        get() = when (state) {
            is Authorized -> true
            is Unauthorized -> false
        }

    val userName: String
        get() {
            return when (val state = this.state) {
                is Authorized -> state.userName
                is Unauthorized -> "Unknown"
            }
        }

    fun loginUser(userName: String) {
        state = Authorized(userName)
    }

    fun logoutUser() {
        state = Unauthorized
    }

    override fun toString(): String = "User $userName is logged in: $isAuthorized"
}

class StateTest() {
    @Test
    fun stateTest() {
        val authorizedPresenter = AuthorizedPresenter()
        authorizedPresenter.loginUser("admin")
        println(authorizedPresenter)
        assertEquals(
            actual = authorizedPresenter.isAuthorized,
            expected = true
        )
        assertEquals(
            actual = authorizedPresenter.userName,
            expected = "admin"
        )

        authorizedPresenter.logoutUser()
        println(authorizedPresenter)
        assertEquals(
            actual = authorizedPresenter.isAuthorized,
            expected = false
        )
        assertEquals(
            actual = authorizedPresenter.userName,
            expected = "Unknown"
        )
    }
}

