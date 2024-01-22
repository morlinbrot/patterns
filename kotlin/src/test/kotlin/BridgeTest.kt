import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BridgeTest {
    @Test
    fun bridgeArtist() {
        val name = "Kurt Kobain"
        val bio = "Lived fast, died young."
        val artistResource = ArtistResource(Artist(name, bio))

        val longView = LongFormView(artistResource)
        val shortView = ShortFormView(artistResource)

        val expectedLong = "Using ${name} and ${bio} for rendering"
        assertEquals(expectedLong, longView.render())

        val expectedShort = "Using ${name} for rendering"
        assertEquals(expectedShort, shortView.render())
    }

    @Test
    fun bridgeAlbum() {
        val title = "Significant Other"
        val desc = "Breaks your fucking face tonight."
        val albumResource = AlbumResource(Album(title, desc))

        val longView = LongFormView(albumResource)
        val shortView = ShortFormView(albumResource)

        val expected = "Using ${title} and ${desc} for rendering"
        assertEquals(expected, longView.render())

        val expectedShort = "Using ${title} for rendering"
        assertEquals(expectedShort, shortView.render())
    }
}