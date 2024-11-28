package models

data class Song(val songTitle: String,
                val songViewCount: Int,
                val songGenre: String,
                val dateOfRelease: Int,
                val songArtist: String){
}