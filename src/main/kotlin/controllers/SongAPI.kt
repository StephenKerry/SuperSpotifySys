package controllers

import models.Song


class SongAPI {
    private var song = ArrayList<Song>()
    fun add(newSong: Song): Boolean {
        return song.add(newSong)
    }
    fun listAllsongs(): String {
        return if (song.isEmpty()) {
            "No songs stored"
        } else {
            var listOfSongs = ""
            for (i in song.indices) {
                listOfSongs += "${i}: ${song[i]} \n"
            }
            listOfSongs
        }
    }
}
fun numberOfSongs(): Int {
    return Song.size
}





//utility method to determine if an index is valid in a list.
fun isValidListTitle(Title: String, list: List<Any>): Boolean {
    return (Title >= 0 && Title < list.size)
}