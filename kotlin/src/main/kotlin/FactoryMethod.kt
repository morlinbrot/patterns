abstract class Logistics {
    // This could also be made non-abstract to provide a default implementation.
    // It could also be parameterized to return a specific kind of Transporter.
    abstract fun createTransporter(): Transporter
}

interface Transporter {
    fun deliver(payload: Int): String
}

class RoadLogistics(): Logistics() {
    override fun createTransporter(): Transporter = Truck()
}

class SeaLogistics(): Logistics() {
    override fun createTransporter(): Transporter = Ship()
}

class Truck: Transporter {
    override fun deliver(payload: Int): String = "Delivering $payload using a truck."
}

class Ship: Transporter {
    override fun deliver(payload: Int): String = "Delivering $payload using a ship."
}
