package models

data class Song(
    val songTitle: String,
    val songViewCount: Int?,
    val songGenre: String,
    val yearOfRelease: Int?,
    val songArtist: String,
    val isSongArchived:Boolean){
}