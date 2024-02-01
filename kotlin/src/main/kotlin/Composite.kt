// The interface that the client uses describes only operations common to all components.
interface IComponent {
    fun getPrice(): Double
}

// Doesn't necessarily need to be its own interface but here for clarity. Another way to do this would be to include the
// tree traversal methods in the base interface, returning default values for primitive components.
interface IComposite {
    fun add(child: IComponent)
    fun getChildren(): List<IComponent>
}

class Box(
    private val children: List<IComponent> = mutableListOf()
): IComponent, IComposite {
    override fun getPrice(): Double {
        var price = 0.0
        for (c in children) {
           price += c.getPrice()
        }
        return price
    }

    override fun add(child: IComponent) = children.addLast(child)

    override fun getChildren(): List<IComponent> = children
}

class Product(private val price: Double): IComponent {
    override fun getPrice(): Double = price
}
