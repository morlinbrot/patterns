import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SingletonTest {
    @Test fun simpleSingleton() {
        val inst1 = SimpleSingleton.instance
        val inst2 = SimpleSingleton.instance

        assertSame(inst1, inst2)
    }

    @Test fun threadSafeSingleton() {
       val inst1 = ThreadSafeSingleton.getInstance()
       val inst2 = ThreadSafeSingleton.getInstance()

       assertSame(inst1, inst2)
    }
}