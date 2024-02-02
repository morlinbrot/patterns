
abstract class Subject(open val pushOrPull: String) {
    private var observers: MutableList<IObserver> = mutableListOf()

    fun subscribe(observer: IObserver) = observers.add(observer)
    fun unsubscribe(observer: IObserver) = observers.remove(observer)
    // For some reason, `notify` throws an "Accidental override" exception.
    fun doNotify() {
        for (o in observers) {
            o.update()
        }
    }

    abstract fun getState(): Int
    abstract fun setState(patch: Int)
}

class ConcreteSubject(
    private var state: Int,
    override val pushOrPull: String = "pull",
): Subject(pushOrPull) {
    override fun getState(): Int = state
    override fun setState(patch: Int) {
        state = patch
        // See discussion of "push" vs "pull" model in the README.
        if (pushOrPull == "push") { doNotify() }
    }
}

interface IObserver {
   fun update()
}

// An observer that simply mirrors the state of its subject, for demonstration purposes.
class ConcreteObserver(
    private val subject: Subject
): IObserver {
    private var state: Int = subject.getState()

    init {
        subject.subscribe(this)
    }

    override fun update() {
        val update = subject.getState()
        state = update
    }

    // Make the state visible to the outside for testing.
    fun getState(): Int = state
}


fun main() {
    // See tests for usage.
}