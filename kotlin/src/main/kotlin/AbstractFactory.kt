// Implementation of the Abstract Factory pattern.
abstract class WidgetFactory {
   abstract fun createScrollBar(): ScrollBar
   abstract fun createPopup(): Popup
}

interface Popup {
    fun pop(): String
}

interface ScrollBar {
    fun scroll(): String
}

// Creates a mobile-specific family of objects.
class MobileWidgetFactory: WidgetFactory() {
    override fun createScrollBar(): ScrollBar = MobileScrollBar()
    override fun createPopup(): Popup = MobilePopup()

    class MobilePopup: Popup {
        override fun pop(): String = "Popping on mobile!"
    }

    class MobileScrollBar: ScrollBar {
        override fun scroll(): String = "Scrolling on mobile!"
    }
}

// Creates a web-specific family of objects.
class WebWidgetFactory: WidgetFactory() {
    override fun createScrollBar(): ScrollBar = WebScrollBar()
    override fun createPopup(): Popup = WebPopup(WebScrollBar())

    // Weird, I know, but only on the web a popup needs a scrollbar. This fact is neatly tucked away inside this concrete
    // factory, the `App` doesn't need to know.
    class WebPopup(scrollBar: ScrollBar): Popup {
        override fun pop(): String = "Popping on the web!"
    }

    class WebScrollBar: ScrollBar {
        override fun scroll(): String = "Scrolling on the web!"
    }
}

// Defines a system of behaviour/interactions based on abstract interfaces only. It doesn't need to know which kind is
// currently in use but created objects will always be from the same family.
class App(var widgetFactory: WidgetFactory) {
    fun changeWidgetFactory(factory: WidgetFactory) {
        widgetFactory = factory
    }

    fun render() {
        val scrollBar = widgetFactory.createScrollBar()
        val popup = widgetFactory.createPopup()
        scrollBar.scroll()
        // We don't need to know that only on the web, a popup will have a scrollbar.
        popup.pop()
    }
}

fun main() {
    // See tests for usage.
}
