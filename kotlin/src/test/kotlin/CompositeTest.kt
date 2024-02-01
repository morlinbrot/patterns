import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test
import kotlin.test.assertEquals

class CompositeTest {

    private fun getObjectsFromDb(): MutableList<IComponent> {
        val box = Box(
            mutableListOf(
                Product(30.0),
                Product(40.0)
            )
        )
        return mutableListOf(
            Product(10.0),
            Product(20.0),
            box,
        )
    }

    @Test
    fun basics() {
        // Let's assume we're retrieving these from a database, e.g. we don't necessarily know if they're a product or
        // a box.
        val objects = getObjectsFromDb()
        val o1 = objects[0]
        assertEquals(10.0, o1.getPrice())
        val o2 = objects[1]
        assertEquals(20.0, o2.getPrice())
        val o3 = objects[2]
        assertEquals(70.0, o3.getPrice())
    }
}