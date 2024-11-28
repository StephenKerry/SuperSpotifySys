import controllers.SongAPI
import io.github.oshai.kotlinlogging.KotlinLogging
import persistence.JSONSerializer
import utils.*
import java.io.File
import java.lang.System.exit

private val logger = KotlinLogging.logger {}
//private val noteAPI = NoteAPI(XMLSerializer(File("song.xml")))
private val SongAPI = SongAPI(JSONSerializer(File("song.json")))
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
fun seeStephenSong() {
    println("You chose to see Stephen's top 5 favorite songs")
    println("""
        1. Bahamas Promises - Drake - https://open.spotify.com/track/3JZjcKImHcmOI9ylL4zrSc?si=nKh-iIC7T2i2No3hrOuJEQ
        2. Last Last - Burna Boy - https://open.spotify.com/track/5YbPxJwPfrj7uswNwoF1pJ?si=Lx6ab5urSkeP8M15BmkP3A
        3. 14 - Baby Santana - https://open.spotify.com/track/4tOypmWdWF0VAjcKfrtpFk?si=5l87NFuFQ1OQwChkGZHOqg
        4. Starlight - Dave - https://open.spotify.com/track/531KGXtBroSrOX9LVmiIgc?si=Nk4pU3TOTR2eGdEI_DfLHw
        5. Soundgasm - Rema - https://open.spotify.com/track/2psl5iNJCrmmm2XtS7LZqb?si=shY7ajISRYaR55rNxy9jCw
    """.trimIndent())
}


fun searchSong(){
    println("You chose Song search")
    val searchedSong = getSongbyTitle()
    if (searchedSong == null)
        println("No employee found")
    else
        println(searchedSong)
}
}
internal fun getSongbyTitle(): searchedSong? {
    print("Enter the song title to search by: ")
    val songTitle = readLine()
    return song.findOne(songTitle)
}


fun addSong(){
    val songTitle = readNextLine("Please a title for your Song: ")
    val songArtist = readNextLine("Who is the Artist for this song?")
    val dateOfRelease = readValidInt("Enter the date of Release for this song: ")
    val songGenre = readNextLine("Enter a Genre for the Song from ${Genres}: ")
    val songViewCount = readNextInt("Enter the view/Stream Count for this song: ")

    val isAdded = SongAPI.add(Song(songTitle, songArtist, dateOfRelease, songGenre, songViewCount false))

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
    if (SongAPI.numberOfsongs() > 0) {
        //only ask the user to choose the note to delete if notes exist
        val indexToDelete = readNextInt("Enter the index of the song to delete: ")
        //pass the index of the note to NoteAPI for deleting and check for success.
        val songToDelete = SongAPI.deleteSong(indexToDelete)
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