// EXAMPLE 1: Logistics app
abstract class Logistics {
    // This could also be made non-abstract to provide a default implementation.
    // It could also be parameterized to return a specific kind of Transporter.
    abstract fun createTransporter(): Transporter
}

interface Transporter {
    fun deliver(payload: Int): String
}

class RoadLogistics(): Logistics() {
    override fun createTransporter(): Transporter = Truck()
}

class SeaLogistics(): Logistics() {
    override fun createTransporter(): Transporter = Ship()
}

class Truck: Transporter {
    override fun deliver(payload: Int): String = "Delivering $payload using a truck."
}

class Ship: Transporter {
    override fun deliver(payload: Int): String = "Delivering $payload using a ship."
}


// EXAMPLE 2: Maze game
open class MazeGame() {
    fun createMaze(): Maze {
        val maze = makeMaze()

        val r1 = makeRoom(1)
        r1.addWalls(makeWalls())
        r1.addDoor(makeDoor())

        val r2 = makeRoom(2)
        r2.addWalls(makeWalls())
        r2.addDoor(makeDoor())

        maze.addRoom(r1)
        maze.addRoom(r2)

        return maze
    }

    private fun makeMaze(): Maze = Maze()
    open fun makeRoom(n: Int): Room = BasicRoom(n)
    open fun makeWalls(): Wall = BasicWall()
    open fun makeDoor(): Door = BasicDoor()
}

class BombedMazeGame : MazeGame() {
    override fun makeRoom(n: Int): Room = BombedRoom(n)
    override fun makeWalls(): Wall = BombedWall()
}

class EnchantedMazeGame: MazeGame() {
    override fun makeRoom(n: Int): Room = EnchantedRoom(n)
    override fun makeDoor(): Door = EnchantedDoor()
}

class Maze {
    private var rooms: List<Room> = listOf()
    fun addRoom(r: Room) {}
}

interface Room {
    fun addWalls(w: Wall) {}
    fun addDoor(d: Door) {}
}
class BasicRoom(val n: Int): Room {}
class BombedRoom(val n: Int): Room {}
class EnchantedRoom(val n: Int): Room {}

interface Wall {}
class BasicWall: Wall
class BombedWall: Wall

interface Door {}
class BasicDoor: Door
class EnchantedDoor: Door