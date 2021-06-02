package au.com.doshii.starwars.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class PlanetResult (
    val count: Long,
    val next: String,
    val previous: Any? = null,
    @SerializedName("results")
    val planets: ArrayList<Planet>
)

data class Planet( val name: String,
                   val rotationPeriod: String,
                   val orbitalPeriod: String,
                   val diameter: String,
                   val climate: String,
                   val gravity: String,
                   val terrain: String,
                   val surfaceWater: String,
                   val population: String,
                   val residents: List<String>,
                   val films: List<String>,
                   val created: String,
                   val edited: String,
                   val url: String)
