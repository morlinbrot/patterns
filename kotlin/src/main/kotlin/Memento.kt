interface MementoNarrow {}

// We are using `internal` as the closes Kotlin approxmiation of something like C++'s `friend`
// keyword which is what we would ideally want to use.
internal interface MementoWide {
    fun setState(s: String)
    fun getState(): String
}

class Memento(private var state: String) : MementoNarrow, MementoWide {
    override fun setState(s: String) {
        state = s
    }

    override fun getState() = state
}

class Originator(private var state: String) {
    fun setState(s: String) {
        state = s
    }

    fun getState() = state

    internal fun createMemento(): Memento {
        return Memento(state)
    }

    internal fun applyMemento(m: MementoWide) {
        state = m.getState()
    }
}

class Caretaker(private var history: MutableList<Memento>) {
    fun push(m: Memento) {
        history.add(m)
    }

    fun pop(): Memento {
        return history.removeAt(history.size - 1)
    }
}
