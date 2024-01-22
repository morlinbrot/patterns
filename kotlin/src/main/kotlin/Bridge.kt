// Example implementation of the Bridge Pattern.
// It is used to decouple an abstraction from its implementation so that both can vary independently.
// Enables us to reduce the cartesian product of the concrete impls of two abstractions to their mere sum.
abstract class View(open val res: Resource) {
    abstract fun render(): String
}

class LongFormView(override val res: Resource) : View(res) {
    override fun render(): String {
        val msg = "Using ${this.res.getTitle()} and ${this.res.getSnippet()} for rendering"
        println(msg)
        return msg
    }
}

class ShortFormView(override val res: Resource) : View(res) {
    override fun render(): String {
        val msg = "Using ${this.res.getTitle()} for rendering"
        println(msg)
        return msg
    }
}

interface Resource {
    fun getTitle(): String = ""
    fun getSnippet(): String = ""
    fun getImage(): String = ""
}

class Artist(val name: String, val bio: String, var albums: List<Album> = listOf())
class Album(val title: String, val desc: String)
class Author(val name: String, val books: List<Book> = listOf())
class Book(val author: Author, val title: String, val coverText: String)

class ArtistResource(
    val artist: Artist,
) : Resource {
    override fun getTitle(): String = this.artist.name
    override fun getSnippet(): String = this.artist.bio
}

class AlbumResource(
    val album: Album
) : Resource {
    override fun getTitle(): String = this.album.title
    override fun getSnippet(): String = this.album.desc
}
