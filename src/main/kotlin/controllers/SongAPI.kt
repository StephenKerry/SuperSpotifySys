package controllers

import models.Song
import persistence.Serializer


class SongAPI (serializerType: Serializer){
    private var serializer: Serializer = serializerType
    private var songs = ArrayList<Song>()
    /**
     * Adds a new [Song] to the list.
     *
     * @param newSong The [Song] to be added.
     *
     */
    fun add(newSong: Song): Boolean {
        return songs.add(newSong)
    }
    fun listAllsongs(): String {
        return if (songs.isEmpty()) {
            "No songs stored"
        } else {
            var listOfSongs = ""
            for (i in songs.indices) {
                listOfSongs += "${i}: ${songs[i]} \n"
            }
            listOfSongs
        }
    }
    fun numberOfSongs(): Int {
        return songs.size
}

fun findOne(songTitle: String?): Song? {
    return songs.find { song -> songTitle.equals(songTitle) }}

    fun deleteSong(songTitle: String): Boolean {
        return songs.removeIf { song -> song.songTitle == songTitle }
    }
    @Throws(Exception::class)
    fun store() {
        serializer.write(songs)
    }
    @Throws(Exception::class)
    fun load() {
        songs = serializer.read() as ArrayList<Song>
    }}







