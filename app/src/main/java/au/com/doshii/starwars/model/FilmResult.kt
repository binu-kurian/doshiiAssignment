package au.com.doshii.starwars.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class FilmResult (
    val count: Long,
    val next: String,
    val previous: Any? = null,
    @SerializedName("results")
    val films: ArrayList<Film>
)

data class Film(    val title: String,
                    val episodeID: Long,
                    val openingCrawl: String,
                    val director: String,
                    val producer: String,
                    val releaseDate: String,
                    val characters: List<String>,
                    val planets: List<String>,
                    val starships: List<String>,
                    val vehicles: List<String>,
                    val species: List<String>,
                    val created: String,
                    val edited: String,
                    val url: String)
