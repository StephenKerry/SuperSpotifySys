package controllers

import models.Song
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import persistence.XMLSerializer
import java.io.File

class SongAPITest {

    private var popSong: Song? = null
    private var rapSong: Song? = null
    private var rockSong: Song? = null


    private var populatedSongs: SongAPI? = SongAPI(XMLSerializer(File("songs.xml")))
    private var emptySongs: SongAPI? = SongAPI(XMLSerializer(File("empty-songs.xml")))

    @BeforeEach
    fun setup() {
        popSong = Song("Shape of You", 290000000, "Pop", 2017, "Ed Sheeran")
        rapSong = Song("In da Club", 30200000, "Rap", 2003, "50 Cent")
        rockSong = Song("Bohemian Rhapsody", 500000000, "Rock", 1975, "Queen")


        // Adding songs to the populatedSongs API
        populatedSongs!!.add(popSong!!)
        populatedSongs!!.add(rapSong!!)
        populatedSongs!!.add(rockSong!!)

    }

    @AfterEach
    fun tearDown() {
        popSong = null
        rapSong = null
        rockSong = null
        populatedSongs = null
        emptySongs = null
    }

    @Nested
    inner class AddSong {

        @Test
        fun `adding a Song to a populated list adds to ArrayList`() {
            val newSong = Song("All I Want for Christmas Is You", 1000000000, "Pop", 1994, "Mariah Carey")
            assertEquals(3, populatedSongs!!.numberOfSongs())
            assertTrue(populatedSongs!!.add(newSong))
            assertEquals(4, populatedSongs!!.numberOfSongs())
        }
    }
    @Test
    fun `listAllSongs returns Songs when ArrayList has songs stored`() {
        assertEquals(3, populatedSongs!!.numberOfSongs())

        val songsString = populatedSongs!!.listAllsongs().lowercase()

        // Check that the song titles are present in the string representation of the songs
        assertTrue(songsString.contains("shape of you")) // Match song title
        assertTrue(songsString.contains("in da club"))
        assertTrue(songsString.contains("bohemian rhapsody"))
    }


}


