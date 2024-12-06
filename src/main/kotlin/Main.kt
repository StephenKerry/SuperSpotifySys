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
fun mainMenu(): Int {// Menu Design from notes app
    println("Welcome to the App")

    print(""" 
         > ----------------------------------
         > |/Stephen's Super Spotify App\|
         > ----------------------------------
         > | MAIN MENU  
         > //////////////////////////////////
         > |  *1) See Stephen's top 5 songs >
         > |  *2) Add a song to the system >
         > |  *3) Search a song and its stats >
         > |  *4) Update Song Details >
         > |  *5) Delete a Song  >
         > |  *6) Save Songs  >
         > |  *7) Load Songs  > 
         > \  *8) draw an Instument
         > ///////////////////////////////////
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
                6 -> save()
                7 -> load()
                8 -> drawInstrument()
                0 -> exitApp()
                else -> println("Invalid option entered: $option")
            }
        } while (true)
    }


 fun drawInstrument() {
     do {
         val option = mainMenu()
         when (option) {
             1 -> drawTrumpet()
             2 -> drawGuitar()
             3 -> drawPiano()
             else -> println("Invalid option entered: $option")
         }
     } while (true)
 }



fun drawPiano() {

        println(
            """
|| | | ||| | ||| | | ||| | ||| | | ||| | ||| | | ||| | ||| | | ||| | ||| | | ||
||_|_|_|||_|_|||_|_|_|||_|_|||_|_|_|||_|_|||_|_|_|||_|_|||_|_|_||
| | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | |
|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|
        """.trimIndent()
        )
    }



fun drawGuitar() {

        println(
            """
                      ,     
                      ,   |     
   _,,._              |  0'     
 ,'     `.__,--.     0'         
/   .--.        |           ,,, 
| [=========|==|==|=|==|=|==___]
\   "--"  __    |           ''' 
 `._   _,'  `--'                
    ""'     ,   ,0     ,        
hjm         |)  |)   ,'|        
  ____     0'   '   | 0'        
  |  |             0'           
 0' 0'               
        """.trimIndent()
        )
    }




fun drawTrumpet() {
        println(
            """/|
=  =  =      / |
____| || || |____/  | -_-_-_-_-_-_
|)----| || || |____   |     AH
((  | || || |  ))\  | _-_-_-_-_-_-
\\_|_||_||_|_//  \ |
\___________/    \|
"""
        )
    }



fun save() {  try { // derived from notes app
    SongAPI.store()
} catch (e: Exception) {
    System.err.println("Error writing to file: $e")
}
}

fun load() { // derived from notes app
    try {
        SongAPI.load()
    } catch (e: Exception) {
        System.err.println("Error reading from file: $e")
    }
}

fun getSongByTitle(): Song? { // for this method i drew inspiration from internal fun getEmployeeById() in the employee app
                             //
    print("Enter the song title to search by: ")
    val songTitle = readLine() // I created a non changable value because I needed to get the users input from this line to ask for their song title
    return SongAPI.findOne(songTitle) // I then used the method in song api and passed the earlier value that i got from the line b4 in
}

fun searchSong() {
    println("Searching a song chosen")
    logger.info { "searchSong() function invoked" }

    val searchedSong = getSongByTitle() //getSongByTitle() should have akready been made as an internakl function but its already done
    if (searchedSong == null) {
        println("No Song found")// if thge user types in a song that is NOT in the system, it should return this method
    } else {
        println("Song found!: $searchedSong")// if uit is found it should return this
    }

}

fun addSong() {// this method derives from the add() method in the employee app
    logger.info { "addSong() function invoked" }
    print("Please a title for your Song: ")
    val songTitle = readLine().toString()
    print("Enter the view/Stream Count for this song: ")
    val songViewCount = readLine()?.toInt()// we are creating values and asking users for their input and converting the returned string to whichever datatype they are in the model songs
    print("Enter a Genre for the Song: ")
    val songGenre = readLine().toString()
    print("Enter a year of Release for the Song: ")
    val yearOfRelease = readLine()?.toInt()
    print("Who is the Artist for this song?")
    val songArtist = readLine().toString()

    val isAdded = SongAPI.add(Song(songTitle, songViewCount, songGenre, yearOfRelease, songArtist))
    // this line is creating a new Song object by passing the users entered values with the help of the adding method in the SongAPI class
    // This then is stored as a value called isAdded

    if (isAdded) {
        println("Added Successfully")
    } else {
        println("Add Failed")
    }
}

fun seeStephenSong() { // A little method I made myself that simply prints my top 5 songs and gives links to their spotify page
    println("Here are Stephens top 5 songs")
    println("""
        1. Bahamas Promises - Drake - https://open.spotify.com/track/3JZjcKImHcmOI9ylL4zrSc?si=nKh-iIC7T2i2No3hrOuJEQ
        2. Last Last - Burna Boy - https://open.spotify.com/track/5YbPxJwPfrj7uswNwoF1pJ?si=Lx6ab5urSkeP8M15BmkP3A
        3. 14 - Baby Santana - https://open.spotify.com/track/4tOypmWdWF0VAjcKfrtpFk?si=5l87NFuFQ1OQwChkGZHOqg
        4. Starlight - Dave - https://open.spotify.com/track/531KGXtBroSrOX9LVmiIgc?si=Nk4pU3TOTR2eGdEI_DfLHw
        5. Soundgasm - Rema - https://open.spotify.com/track/2psl5iNJCrmmm2XtS7LZqb?si=shY7ajISRYaR55rNxy9jCw
    """.trimIndent())
}

fun deleteSong() {
    println("You chos Deleting a song")
    logger.info { "deleteSong() function invoked" }
    //Message for ehn a user chooses the dele song method
    println(SongAPI.listAllsongs())
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
    exit(0) // derived from notes app
}

fun updateSong() {
    println("Updating a song")
    //logger.info { "updateSong() function invoked" }
    logger.info { "updateSong() function invoked" }
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



                songToUpdate.songTitle = songTitle // This section the new values are getting passed in
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










