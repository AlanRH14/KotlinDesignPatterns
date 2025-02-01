package behavioural_patterns

import java.io.File
import kotlin.test.Test

interface EventListener {
    fun update(eventType: String?, file: File?)
}

class EventManager(vararg operations: String) {
    private var listeners = hashMapOf<String, ArrayList<EventListener>>()

    init {
        for (operation in operations) {
            listeners[operation] = ArrayList()
        }
    }

    fun subscribe(eventType: String, listener: EventListener) {
        val users = listeners[eventType]
        users?.add(listener)
    }

    fun unSubscribe(eventType: String, listener: EventListener) {
        val users = listeners[eventType]
        users?.remove(listener)
    }

    fun notify(eventType: String?, file: File) {
        val user = listeners[eventType]
        user?.let { userListener ->
            for (listener in userListener) {
                listener.update(eventType, file)
            }
        }
    }
}

class Editor {
    var events = EventManager("open", "save")
    private var file: File? = null

    fun openFile(filePath: String) {
        file = File(filePath)
        file?.let { mFile ->
            events.notify("open", mFile)
        }
    }

    fun saveFile() {
        file?.let { mFile ->
            events.notify("save", mFile)
        }
    }
}

class EmailNotificationListener(private val email: String) : EventListener {
    override fun update(eventType: String?, file: File?) {
        println("Email to $email: Someone has performed $eventType operation with the file ${file?.name}")
    }
}

class LogOpenListener(private val fileName: String) : EventListener {
    override fun update(eventType: String?, file: File?) {
        println("Save to log $fileName: Someone has performed $eventType operation with the file ${file?.name}")
    }
}

class ObserverTest {
    @Test
    fun observerTest() {
        val editor = Editor()

        val logOpenListener = LogOpenListener("path/to/log/file.text")
        val emailNotificationListener = EmailNotificationListener("test@test.com")
        editor.events.subscribe(eventType = "open", listener = logOpenListener)
        editor.events.subscribe(eventType = "open", listener = emailNotificationListener)
        editor.events.subscribe(eventType = "save", listener = emailNotificationListener)

        editor.openFile("test.text")
        editor.saveFile()
    }
}