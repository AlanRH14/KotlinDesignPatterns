package behavioural_patterns

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

interface HandlerChain {
    fun addHeader(inputHeader: String): String
}

class AuthenticationHeader(val token: String?, var next: HandlerChain? = null) : HandlerChain {
    override fun addHeader(inputHeader: String) =
        "$inputHeader\nAuthorization: $token"
            .let { next?.addHeader(it) ?: it }
}

class ContentTypeHeader(val contentType: String, var next: HandlerChain? = null) : HandlerChain {
    override fun addHeader(inputHeader: String): String =
        "$inputHeader\nContentType: $contentType"
            .let { next?.addHeader(it) ?: it }
}

class BodyPayloadHeader(val body: String, val next: HandlerChain? = null) : HandlerChain {
    override fun addHeader(inputHeader: String): String =
        "$inputHeader\n$body"
            .let { next?.addHeader(it) ?: it }
}

class ChainOfResponsibilityTest {
    @Test
    fun chainOfResponsibilityTest() {
        val authenticationHeader = AuthenticationHeader("12345")
        val contentTypeHeader = ContentTypeHeader("json")
        val bodyPayloadHeader = BodyPayloadHeader("Body: {\"username\" = \"LordMiau\"}")

        authenticationHeader.next = contentTypeHeader
        contentTypeHeader.next = bodyPayloadHeader

        val messageWithAuthentication = authenticationHeader.addHeader("Headers with authentication")
        println(messageWithAuthentication)

        println("--------------------------")

        val messageWithoutAuthentication = contentTypeHeader.addHeader("Headers without authentication")
        println(messageWithoutAuthentication)

        assertEquals(
            actual = messageWithoutAuthentication,
            expected =
                """
                    Headers without authentication
                    ContentType: json
                    Body: {"username" = "LordMiau"}
                """.trimIndent()
        )

        assertEquals(
            actual = messageWithAuthentication,
            expected =
                """
                    Headers with authentication
                    Authorization: 12345
                    ContentType: json
                    Body: {"username" = "LordMiau"}
                """.trimIndent()
        )
    }
}