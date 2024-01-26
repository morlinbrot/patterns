import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PrototypeTest {
    @Test
    fun shapes() {
        val circle = Circle(2);
        val clone = circle.clone()
        assertEquals(circle.area(), clone.area())

        val square = Square(4.0)
        val clone2 = square.clone()
        assertEquals(square.area(), clone2.area())
    }
}