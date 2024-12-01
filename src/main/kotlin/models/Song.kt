package models

data class Song(
    var songTitle: String,
    var songViewCount: Int?,
    var songGenre: String,
    var yearOfRelease: Int?,
    var songArtist: String,
    var isSongArchived:Boolean){
}