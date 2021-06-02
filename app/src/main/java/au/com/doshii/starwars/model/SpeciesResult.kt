package au.com.doshii.starwars.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class SpeciesResult (
    val count: Long,
    val next: String,
    val previous: Any? = null,
    @SerializedName("results")
    val speciess: ArrayList<Species>
)

data class Species( val name: String,
                    val classification: String,
                    val designation: String,
                    val averageHeight: String,
                    val skinColors: String,
                    val hairColors: String,
                    val eyeColors: String,
                    val averageLifespan: String,
                    val homeworld: String? = null,
                    val language: String,
                    val people: List<String>,
                    val films: List<String>,
                    val created: String,
                    val edited: String,
                    val url: String)
