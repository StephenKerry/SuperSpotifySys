package setu.ie
fun main() {
    runMenu()
}
fun mainMenu(): Int {
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
    return readNextInt(" > ==>>")
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