import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ObserverTest {
    @Test
    fun pullModel() {
        val sub = ConcreteSubject(0)
        val obs = ConcreteObserver(sub)

        Assertions.assertEquals(0, sub.getState())
        Assertions.assertEquals(0, obs.getState())

        sub.setState(1)
        Assertions.assertEquals(1, sub.getState())
        Assertions.assertEquals(0, obs.getState())

        sub.doNotify()
        Assertions.assertEquals(1, obs.getState())
    }

    @Test
    fun pushModel() {
        val sub = ConcreteSubject(0, "push")
        val obs = ConcreteObserver(sub)

        assertEquals(0, sub.getState())
        assertEquals(0, obs.getState())

        sub.setState(1)
        assertEquals(1, sub.getState())
        assertEquals(1, obs.getState())
    }
}