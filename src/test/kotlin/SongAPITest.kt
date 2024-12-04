package controllers

import models.Song
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import persistence.XMLSerializer
import java.io.File

class SongAPITest {

    private var PopSong: Song? = null
    private var RapSong: Song? = null
    private var populatedSongs: SongAPI? = SongAPI(XMLSerializer(File("songs.xml")))


    @BeforeEach
    fun setup() {
        PopSong = Song("Shape of You", 290000000, "Pop", 2017, "Ed Sheeran")
        RapSong = Song("In da Club", 30200000, "Rap",2003, "50 Cent" )


        // adding 5 Songs to the songs api
        populatedSongs!!.add(PopSong!!)
        populatedSongs!!.add(RapSong!!)

    }

    @AfterEach
    fun tearDown() {
        PopSong = null
        RapSong= null
        populatedSongs = null

    }

    @Nested

    inner class addSong{
        @Test
        fun `adding a Song to a populated list adds to ArrayList`() {
            val newSong = Song("All I want for christmas", 1000000000, "Pop", 1994, "Mariah Carey")
            assertEquals(5, populatedSongs!!.numberOfSongs())
            assertTrue(populatedSongs!!.add(newSong))
            assertEquals(6, populatedSongs!!.numberOfSongs())
        }

    }}
