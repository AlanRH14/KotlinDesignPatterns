package behavioural_patterns

import kotlin.test.Test

class ChatUser(private val mediator: ChatMediator, private val name: String) {
    fun send(msg: String) {
        println("$name: Sending message: $msg")
        mediator.sendMessage(msg = msg, user = this)
    }

    fun receive(msg: String) {
        println("$name: Received message: $msg")
    }
}

class ChatMediator {
    private val users = arrayListOf<ChatUser>()

    fun sendMessage(msg: String, user: ChatUser) {
        users.filter { it != user }
            .forEach { it.receive(msg) }
    }

    fun addUser(user: ChatUser): ChatMediator = apply { users.add(user) }
}

class MediatorTest {
    @Test
    fun mediatorTest() {
        val mediator  = ChatMediator()
        val lordMiau  = ChatUser(mediator = mediator, name = "LordMiau")
        val nameTest = ChatUser(mediator = mediator, name = "Name Test")
        val nameTest2 = ChatUser(mediator = mediator, name = "Name Test 2")

        mediator.addUser(lordMiau)
            .addUser(nameTest)
            .addUser(nameTest2)

        lordMiau.send("Hello everyone")
    }
}