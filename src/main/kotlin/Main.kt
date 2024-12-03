import controllers.SongAPI
import io.github.oshai.kotlinlogging.KotlinLogging
import models.Song
import java.lang.System.exit
import utils.readNextInt
import persistence.JSONSerializer
import java.io.File

private val logger = KotlinLogging.logger {}
private val SongAPI = SongAPI(JSONSerializer(File("songs.json")))

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
                2 -> addSong()
                3 -> searchSong()
                4 -> updateSong()
                5 -> deleteSong()
                6 -> saveSongs()
                7 -> loadSongs()
                0 -> exitApp()
                else -> println("Invalid option entered: $option")
            }
        } while (true)
    }

fun getSongByTitle(): Song? {
    print("Enter the song title to search by: ")
    val songTitle = readLine()
    return SongAPI.findOne(songTitle)
}

fun searchSong() {
    println("You chose Song search")

    val searchedSong = getSongByTitle()
    if (searchedSong == null) {
        println("No Song found")
    } else {
        println("Song found!: $searchedSong")
    }

}

fun addSong() {
    print("Please a title for your Song: ")
    val songTitle = readLine().toString()
    print("Enter the view/Stream Count for this song: ")
    val songViewCount = readLine()?.toInt()
    print("Enter a Genre for the Song: ")
    val songGenre = readLine().toString()
    print("Enter a year of Release for the Song: ")
    val yearOfRelease = readLine()?.toInt()
    print("Who is the Artist for this song?")
    val songArtist = readLine().toString()

    val isAdded = SongAPI.add(Song(songTitle, songViewCount, songGenre, yearOfRelease, songArtist, false))

    if (isAdded) {
        println("Added Successfully")
    } else {
        println("Add Failed")
    }
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

fun deleteSong() {
    println("You chose Delete a song")
    //Message for ehn a user chooses the dele song method
    SongAPI.listAllsongs()
    if (SongAPI.numberOfSongs() > 0) {
        print("Enter the Title of the Song to delete: ")
        val titleToDelete = readLine()
        if (titleToDelete != null) {
            val songToDelete = SongAPI.findOne(titleToDelete) // Find the song by title
        if (songToDelete != null) {
            SongAPI.deleteSong(titleToDelete)
        }
            println("Song has been deleted successfully!")
        } else {
            println("Delete was NOT Successful")
        }
    }
}

fun exitApp() {
    println("System shutting down...")
    exit(0)
}

fun updateSong() {
    println("You chose Update a song")
    //logger.info { "updateSong() function invoked" }
     SongAPI.listAllsongs()
    if (SongAPI.numberOfSongs() > 0) {
        print("Enter the Title of the Song to update: ")
        val titleToUpdate = readLine()
        if (titleToUpdate != null) {
            val songToUpdate = SongAPI.findOne(titleToUpdate) // Find the song by title

            if (songToUpdate != null) {
                // Gather updated details for the song
            print("Please a new title for your Song: ")
            val songTitle = readLine().toString()
            print("Enter the new view/Stream Count for this song: ")
            val songViewCount = readLine()?.toInt()
            print("Enter a  new Genre for the Song : ")
            val songGenre = readLine().toString()
            print("Enter the new year of release for the Song : ")
            val yearOfRelease = readLine()?.toInt()
            print("Who is the new Artist for this song?")
            val songArtist = readLine().toString()



                songToUpdate.songTitle = songTitle
                songToUpdate.songViewCount = songViewCount
                songToUpdate.songGenre= songGenre
                songToUpdate.yearOfRelease = yearOfRelease
                songToUpdate.songArtist = songArtist

            } else {
            println("There are no songs with this title")
        }
    }
}




fun listSong() {
    println(SongAPI.listAllsongs())
}



}










