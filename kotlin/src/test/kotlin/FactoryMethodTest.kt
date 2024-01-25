import kotlin.test.Test
import kotlin.test.assertEquals

class FactoryMethodTest {
    @Test
    fun roadLogistics() {
        // We are a startup that does logistics. At first, we were only using trucks to do deliveries.
        val logistics = RoadLogistics()
        val transporter = logistics.createTransporter()
        val delivery = transporter.deliver(42)
        assertEquals("Delivering 42 using a truck.", delivery)
    }

    @Test
    fun seaLogistics() {
        // Later, we expanded into the maritime sector. Lucky for us, the architect at the time specified our logistics
        // module to be coded against an interface. Today, our juniors can extend its functionality easily.
        val logistics = SeaLogistics()
        val transporter = logistics.createTransporter()
        // Other parts of our now crazily complex web of microservices don't even know how deliveries are being made.
        val delivery = transporter.deliver(69)
        assertEquals("Delivering 69 using a ship.", delivery)
    }
}