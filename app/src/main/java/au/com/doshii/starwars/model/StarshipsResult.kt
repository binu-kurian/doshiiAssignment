package au.com.doshii.starwars.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class StarShipResult (
    val count: Long,
    val next: String,
    val previous: Any? = null,
    @SerializedName("results")
    val starShips: ArrayList<StarShip>
)

data class StarShip(  val name: String,
                      val model: String,
                      val manufacturer: String,
                      val costInCredits: String,
                      val length: String,
                      val maxAtmospheringSpeed: String,
                      val crew: String,
                      val passengers: String,
                      val cargoCapacity: String,
                      val consumables: String,
                      val hyperdriveRating: String,
                      val mglt: String,
                      val starshipClass: String,
                      val pilots: List<String>,
                      val films: List<String>,
                      val created: String,
                      val edited: String,
                      val url: String)
