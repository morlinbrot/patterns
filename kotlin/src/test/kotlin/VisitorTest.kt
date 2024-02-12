import kotlin.test.Test
import kotlin.test.assertEquals

class VisitorTest {
    @Test
    fun basics() {
        val nodes = listOf(City(), Industry())

        val xmlVisitor = ExportXMLVisitor()

        for (n in nodes) {
            n.accept(xmlVisitor)
        }

        val exports = xmlVisitor.exports
        assertEquals(2, xmlVisitor.exports.size)
        assertEquals("Description of a city.", exports[0].description)
        assertEquals("Description of an industry.", exports[1].description)
    }
}
