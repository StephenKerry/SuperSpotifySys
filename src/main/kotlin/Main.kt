import controllers.NoteAPI
import io.github.oshai.kotlinlogging.KotlinLogging
import models.Note
import persistence.JSONSerializer
import persistence.XMLSerializer
import utils.*
import java.io.File
import java.lang.System.exit

private val logger = KotlinLogging.logger {}
//private val noteAPI = NoteAPI(XMLSerializer(File("song.xml")))
private val noteAPI = NoteAPI(JSONSerializer(File("song.json")))
fun main() {
    runMenu()
}
fun mainMenu(): Int {
    println("Welcome to the App")

    print(""" 
         > ----------------------------------
         > |**Stephen's Super Spotify App**  |
         > ----------------------------------
         > | MAIN MENU                      |
         > |  *1) See Stephen's top 5 songs >
         > |  *2) Add a song to the system >
         > |  *3) Search a song and its stats >
         > |  *4) Update Song Details >
         > |   *5) Delete a Song  >
         > 
         > ----------------------------------
         > |   *0) Exit                      |
         > ---------------------------------- 
         >""".trimMargin(">"))
    return readNextInt(" > ==>>") }
    fun runMenu() {
        do {
            val option = mainMenu()
            when (option) {
                1 -> seeStephenSong()
                2 -> addSong ()
                3 -> searchSong()
                4 -> updateSong()
                5 -> deleteSong()
                0 -> exitApp()
                else -> println("Invalid option entered: $option")
            }
        } while (true)
    }
fun seeStephenSong(){
    println("You chose Add Note")
}

fun searchSong(){
    println("You chose Song search")
}

fun addSong(){
    val songTitle = readNextLine("Please a title for your Song: ")
    val songArtist = readNextLine("Who is the Artist for this song?")
    val dateOfRelease = readValidPriority("Enter the date of Release for this song: ")
    val songGenre = readValidGenre("Enter a Genre for the Song from ${Genres}: ")
    val songViewCount = readNextInt("Enter the view/Stream Count for this song: ")

    val isAdded = songAPI.add(Song(songTitle, songArtist, dateOfRelease, songGenre, songViewCount false))

    if (isAdded) {
        println("Added Successfully")
    } else {
        println("Add Failed")
    }
}

}

fun deleteSong(){
    println("You chose Delete a song")
    //logger.info { "deleteSong() function invoked" }
    listSong()
    if (SongAPI.numberOfNotes() > 0) {
        //only ask the user to choose the note to delete if notes exist
        val indexToDelete = readNextInt("Enter the index of the song to delete: ")
        //pass the index of the note to NoteAPI for deleting and check for success.
        val songToDelete = noteAPI.deleteSong(indexToDelete)
        if (songToDelete != null) {
            println("Song has been deleted successfully! Deleted song: ${songToDelete.songTitle}")
        } else {
            println("Delete was NOT Successful")
        }
    }
}
}
fun updateSong(){
    println("You chose Update a song")
}
fun exitApp(){
    println("System shutting down...")
    exit(0)
}