import kotlin.test.Test
import kotlin.test.assertEquals

class AdapterTest {
    @Test
    fun basics() {
        val myKnownObject = TargetClass()
        val data = Data(42)
        assertEquals("someMethod called with 42.", myKnownObject.someMethod(data))

        val someOutsideType = Adaptee()
        val adapter = Adapter(someOutsideType)
        assertEquals("specialMethod called with 42.", adapter.someMethod(data))
    }
}