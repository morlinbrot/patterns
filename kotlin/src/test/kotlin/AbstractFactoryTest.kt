import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class AbstractFactoryTest {
    @Test fun basics() {
        val app = App(MobileWidgetFactory())
        var popup = app.widgetFactory.createPopup()
        kotlin.test.assertEquals(popup.pop(), "Popping on mobile!")

        app.changeWidgetFactory(WebWidgetFactory())
        popup = app.widgetFactory.createPopup()
        kotlin.test.assertEquals(popup.pop(), "Popping on the web!")
    }
}