package controllers

import models.Note
import models.Song

class SongAPI {
    private var song = ArrayList<Song>()
    fun add(note: Note): Boolean {
        return song.add(note)
    }
    fun listAllNotes(): String {
        return if (notes.isEmpty()) {
            "No notes stored"
        } else {
            var listOfNotes = ""
            for (i in notes.indices) {
                listOfNotes += "${i}: ${notes[i]} \n"
            }
            listOfNotes
        }
    }
}
fun numberOfNotes(): Int {
    return notes.size
}

fun findNote(index: String): Song? {
    return if (isValidListTitle(index, Song)) {
        Song[index]
    } else null
}

//utility method to determine if an index is valid in a list.
fun isValidListTitle(Title: String, list: List<Any>): Boolean {
    return (Title >= 0 && Title < list.size)
}