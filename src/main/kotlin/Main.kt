package setu.ie

fun mainMenu(): Int {
    print(""" 
         > ----------------------------------
         > |**Stephen's Super Spotify App**  |
         > ----------------------------------
         > | MAIN MENU                      |
         > |  *1) See Stephen's top 5 songs >
         > |  *2) Listing your top 5 songs in chronological order >
         > |   *3) Ranking your top 5 Artists >
         > |   *4) Turning Yearly listen time from minutes into Days, Weeks and Seconds >
         > ----------------------------------
         > |   *0) Exit                      |
         > ---------------------------------- 
         >""".trimMargin(">"))
    return readNextInt(" > ==>>")