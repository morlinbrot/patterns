
class SimpleSingleton private constructor() {
    companion object {
        val instance:SimpleSingleton by lazy {
            SimpleSingleton()
        }
    }
}

class ThreadSafeSingleton private constructor() {
    companion object {
        @Volatile
        private var instance: ThreadSafeSingleton? = null

        fun getInstance(): ThreadSafeSingleton {
            return instance ?: synchronized(this) {
                instance ?: ThreadSafeSingleton().also { instance = it }
            }
        }
    }
}
