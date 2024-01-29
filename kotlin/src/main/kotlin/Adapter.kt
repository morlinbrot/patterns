
// This is our domain-specific "internal" interface that our program uses
data class Data(val x: Int)

open class TargetClass() {
    open fun someMethod(data: Data): String = "someMethod called with ${data.x}."
}

// This is the external interface that we want to adapt to the internal one.
data class SpecialData(val y: String)

class Adaptee() {
    fun specialMethod(specialData: SpecialData): String = "specialMethod called with ${specialData.y}."
}

// We are using composition to adapt the interface.
// This is the object-based variant of the pattern since Kotlin doesn't allow multiple inheritance.
class Adapter(private val adaptee: Adaptee): TargetClass() {
    override fun someMethod(data: Data): String {
        val converted = SpecialData(data.x.toString())
        return adaptee.specialMethod(converted)
    }
}
