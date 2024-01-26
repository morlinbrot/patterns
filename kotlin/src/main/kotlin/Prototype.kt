// EXAMPLE 1: Shapes

interface Prototype<T> {
    fun clone(): T
}

interface Shape {
    fun area(): Double
}

class Circle(private val radius: Int): Shape, Prototype<Circle> {
    // Kotlin allows constructor overloading which we can use to construct a new shape from another one.
    // We use this in the `clone` impls to make sure we always properly construct a new object before handing it out.
    constructor(other: Circle) : this(other.radius)
    // We have access to private fields here.
    override fun clone(): Circle = Circle(this)

    override fun area(): Double = Math.PI * radius * radius
}

class Square(private val length: Double): Shape, Prototype<Square> {
    constructor(other: Square) : this(other.length)
    override fun clone(): Square = Square(this)

    override fun area(): Double = length * length
}


// EXAMPLE 2: Maze prototype factory

// Subclass of the "non-prototypical" `MazeFactory` from the Abstract Factory pattern.
class MazePrototypeFactory {

}