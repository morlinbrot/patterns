import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MementoTest {
    @Test
    fun bridgeArtist() {
        val o = Originator("Foo")
        val c = Caretaker(mutableListOf())

        assertEquals("Foo", o.getState())

        c.push(o.createMemento())

        o.setState("Bar")
        assertEquals("Bar", o.getState())

        o.applyMemento(c.pop())
        assertEquals("Foo", o.getState())
    }
}
