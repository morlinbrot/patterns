interface INode {
    fun accept(v: Visitor)
}

class City : INode {
    override fun accept(v: Visitor) {
        v.visitCity(this)
    }

    fun describeCity(): String = "Description of a city."
}

class Industry : INode {
    override fun accept(v: Visitor) {
        v.visitIndustry(this)
    }

    fun describeIndustry(): String = "Description of an industry."
}

data class XMLExport(val type: String, val description: String)

abstract class Visitor {
    abstract fun visitCity(c: City)
    abstract fun visitIndustry(i: Industry)
}

class ExportXMLVisitor : Visitor() {
    var exports = mutableListOf<XMLExport>()

    override fun visitCity(c: City) {
        exports.add(XMLExport(type = "City", description = c.describeCity()))
    }

    override fun visitIndustry(i: Industry) {
        exports.add(XMLExport(type = "Industry", description = i.describeIndustry()))
    }
}

fun main() {
    // See tests for usage.
}
